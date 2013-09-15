package de.flaviait.tasks.vendingmachine.serviceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.flaviait.tasks.vendingmachine.domain.Coin;
import de.flaviait.tasks.vendingmachine.domain.CoinSlot;
import de.flaviait.tasks.vendingmachine.domain.Drink;
import de.flaviait.tasks.vendingmachine.domain.DrinkAndChange;
import de.flaviait.tasks.vendingmachine.domain.DrinkSlot;
import de.flaviait.tasks.vendingmachine.domain.PermissibleCoins;
import de.flaviait.tasks.vendingmachine.exceptions.DrinkUnavailableException;
import de.flaviait.tasks.vendingmachine.exceptions.ChangeUnvailableException;
import de.flaviait.tasks.vendingmachine.exceptions.InsufficientCoinsException;
import de.flaviait.tasks.vendingmachine.service.VendingMachine;

/**
 * 
 * @author <a href="mailto:acsama@gmail.com.de">Achiri Sama Teyha</a>
 * 
 */
public class VendingMachineImpl implements VendingMachine {

	// Logger
	private static final Log LOGGER = LogFactory
			.getLog(VendingMachineImpl.class);

	public Map<Long, DrinkSlot> drinkStore;
	public Map<PermissibleCoins, CoinSlot> coinStore;

	/**
	 * default constructor, initializes an in memory store of drinks and coins
	 */
	public VendingMachineImpl() {
		super();
		initStore();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.flaviait.tasks.vendingmachine.service.VendingMachineService#buy(de
	 * .flaviait.tasks.vendingmachine.domain.Drink,
	 * de.flaviait.tasks.vendingmachine.domain.Coin[])
	 */
	public DrinkAndChange buy(Drink selectedDrink, Coin... inputCoin)
			throws ChangeUnvailableException, DrinkUnavailableException,
			InsufficientCoinsException {

		BigDecimal paidPrice = BigDecimal.ZERO;

		paidPrice = getPaidPrice(inputCoin);

		Coin[] change = null;

		BigDecimal costPrice = drinkStore.get(selectedDrink.getSlotNumber())
				.getPrice();

		if (paidPrice.compareTo(costPrice) < 0) {
			String message = String
					.format("sorry but the drink you have choosen cost %d cents and not %d cents",
							costPrice.intValue(), paidPrice.intValue());
			LOGGER.info(message);
			throw new InsufficientCoinsException(message);
		} else if (paidPrice.compareTo(costPrice) > 0) {
			change = getChange(costPrice, paidPrice);
		}

		selectedDrink = dispenseDrink(selectedDrink);

		return new DrinkAndChange(selectedDrink, change);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.flaviait.tasks.vendingmachine.service.VendingMachineService#
	 * replenishDrinks(de.flaviait.tasks.vendingmachine.domain.Drink)
	 */
	public void replenishDrinks(Drink drink) {
		drinkStore.get(drink.getSlotNumber()).increaseCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.flaviait.tasks.vendingmachine.service.VendingMachineService#
	 * replenishChange(de.flaviait.tasks.vendingmachine.domain.Coin)
	 */
	public void replenishChange(Coin change) {
		coinStore.get(change.getDenomination()).increaseCount();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.flaviait.tasks.vendingmachine.service.VendingMachineService#
	 * emptyDrinkStore()
	 */
	public void emptyDrinkStore() {
		for (Map.Entry<Long, DrinkSlot> drinkSlot : drinkStore.entrySet()) {
			drinkSlot.getValue().setCount(0);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.flaviait.tasks.vendingmachine.service.VendingMachineService#
	 * emptyChangeStore()
	 */
	public void emptyChangeStore() {

		for (Map.Entry<PermissibleCoins, CoinSlot> coinSlot : coinStore
				.entrySet()) {
			coinSlot.getValue().setCount(0);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.flaviait.tasks.vendingmachine.service.VendingMachineService#
	 * getCoinInventory(de.flaviait.tasks.vendingmachine.domain.Drink)
	 */
	public int getCoinInventory(Coin coin) {
		return coinStore.get(coin.getDenomination()).getCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.flaviait.tasks.vendingmachine.service.VendingMachineService#
	 * getDrinkInventory(de.flaviait.tasks.vendingmachine.domain.Coin)
	 */
	public int getDrinkInventory(Drink drink) {
		return drinkStore.get(drink.getSlotNumber()).getCount();
	}

	private Drink dispenseDrink(Drink selectedDrink)
			throws DrinkUnavailableException {

		DrinkSlot selectedDrinkSlot = drinkStore.get(selectedDrink
				.getSlotNumber());

		if (selectedDrinkSlot != null && selectedDrinkSlot.getCount() > 0) {

			selectedDrinkSlot.decreaseCount();

			return new Drink(selectedDrinkSlot.getSlotNumber(),
					selectedDrinkSlot.getName());
		}

		throw new DrinkUnavailableException();
	}

	private BigDecimal getPaidPrice(Coin... receivedCoins) {

		BigDecimal paidPrice = BigDecimal.ZERO;

		for (Coin coin : receivedCoins) {

			switch (coin.getDenomination()) {
			case CENT10:
				paidPrice = paidPrice.add(PermissibleCoins.CENT10.getValue());
				break;
			case CENT20:
				paidPrice = paidPrice.add(PermissibleCoins.CENT20.getValue());
				break;
			case CENT50:
				paidPrice = paidPrice.add(PermissibleCoins.CENT50.getValue());
				break;
			case ONE_EURO:
				paidPrice = paidPrice.add(PermissibleCoins.ONE_EURO.getValue());
				break;
			case TWO_EUROS:
				paidPrice = paidPrice
						.add(PermissibleCoins.TWO_EUROS.getValue());
				break;

			default:
				break;
			}
		}
		return paidPrice;
	}

	private Coin[] getChange(BigDecimal costPrice, BigDecimal paidPrice)
			throws ChangeUnvailableException {

		BigDecimal balance = paidPrice.subtract(costPrice);

		List<Coin> changeCoins = new ArrayList<Coin>();

		boolean isChangeAvailable = false;

		if (balance.divide(PermissibleCoins.TWO_EUROS.getValue(),
				RoundingMode.HALF_UP).intValue() >= 1) {

			int twoEuroChangeCount = balance
					.divide(PermissibleCoins.TWO_EUROS.getValue(),
							RoundingMode.HALF_UP).intValue();

			isChangeAvailable = isChangeAvailable(PermissibleCoins.TWO_EUROS,
					changeCoins, twoEuroChangeCount);

			if (isChangeAvailable) {
				balance = balance.subtract(PermissibleCoins.TWO_EUROS
						.getValue().multiply(
								BigDecimal.valueOf(twoEuroChangeCount)));
			}
		}

		if (balance.divide(PermissibleCoins.ONE_EURO.getValue(),
				RoundingMode.HALF_UP).intValue() >= 1) {

			int oneEuroChangeCount = balance.divide(
					PermissibleCoins.ONE_EURO.getValue(), RoundingMode.HALF_UP)
					.intValue();

			isChangeAvailable = isChangeAvailable(PermissibleCoins.ONE_EURO,
					changeCoins, oneEuroChangeCount);
			if (isChangeAvailable) {
				balance = balance.subtract(PermissibleCoins.TWO_EUROS
						.getValue().multiply(
								BigDecimal.valueOf(oneEuroChangeCount)));
			}
		}

		if (balance.divide(PermissibleCoins.CENT50.getValue(),
				RoundingMode.HALF_UP).intValue() >= 1) {

			int fiftyCentChangeCount = balance.divide(
					PermissibleCoins.CENT50.getValue(), RoundingMode.HALF_UP)
					.intValue();

			isChangeAvailable = isChangeAvailable(PermissibleCoins.CENT50,
					changeCoins, fiftyCentChangeCount);
			if (isChangeAvailable) {
				balance = balance.subtract(PermissibleCoins.CENT50.getValue()
						.multiply(BigDecimal.valueOf(fiftyCentChangeCount)));
			}
		}

		if (balance.divide(PermissibleCoins.CENT20.getValue(),
				RoundingMode.HALF_UP).intValue() >= 1) {

			int twentyCentChangeCount = balance.divide(
					PermissibleCoins.CENT20.getValue(), RoundingMode.HALF_UP)
					.intValue();

			isChangeAvailable = isChangeAvailable(PermissibleCoins.CENT20,
					changeCoins, twentyCentChangeCount);
			if (isChangeAvailable) {
				balance = balance.subtract(PermissibleCoins.CENT20.getValue()
						.multiply(BigDecimal.valueOf(twentyCentChangeCount)));
			}
		}

		if (balance.divide(PermissibleCoins.CENT10.getValue(),
				RoundingMode.HALF_UP).intValue() >= 1) {

			int tenCentChangeCount = balance.divide(
					PermissibleCoins.CENT10.getValue(), RoundingMode.HALF_UP)
					.intValue();

			isChangeAvailable = isChangeAvailable(PermissibleCoins.CENT10,
					changeCoins, tenCentChangeCount);

			if (isChangeAvailable) {
				balance = balance.subtract(PermissibleCoins.CENT10.getValue()
						.multiply(BigDecimal.valueOf(tenCentChangeCount)));
			} else {
				throw new ChangeUnvailableException(
						"insufficient coins for change");
			}
		}

		return changeCoins.toArray(new Coin[changeCoins.size()]);
	}

	private boolean isChangeAvailable(PermissibleCoins permissibleCoins,
			List<Coin> changeCoins, int coinCount) {

		CoinSlot neededCoin = coinStore.get(permissibleCoins);

		if (neededCoin.getCount() >= coinCount) {
			for (int i = 0; i < coinCount; i++) {
				Coin changeCoin = new Coin(permissibleCoins);
				changeCoins.add(changeCoin);
				neededCoin.decreaseCount();
			}
			return true;
		}

		LOGGER.warn(permissibleCoins.getName()
				+ "s out of stock; please replenish");
		return false;
	}

	private void initStore() {
		DrinkSlot coke = new DrinkSlot(101L, "Coke", 10,
				BigDecimal.valueOf(120));

		DrinkSlot dietCoke = new DrinkSlot(201L, "Diet Coke", 10,
				BigDecimal.valueOf(160));

		DrinkSlot fanta = new DrinkSlot(301L, "Fanta", 10,
				BigDecimal.valueOf(70));

		DrinkSlot sprite = new DrinkSlot(401L, "Sprite", 10,
				BigDecimal.valueOf(100));

		DrinkSlot pepsiCola = new DrinkSlot(501L, "Pepsi Cola", 10,
				BigDecimal.valueOf(120));

		drinkStore = new HashMap<Long, DrinkSlot>();
		drinkStore.put(101L, coke);
		drinkStore.put(201L, dietCoke);
		drinkStore.put(301L, fanta);
		drinkStore.put(401L, sprite);
		drinkStore.put(501L, pepsiCola);

		CoinSlot cent10 = new CoinSlot(PermissibleCoins.CENT10, 100);
		CoinSlot cent20 = new CoinSlot(PermissibleCoins.CENT20, 100);
		CoinSlot cent50 = new CoinSlot(PermissibleCoins.CENT50, 100);
		CoinSlot cent100 = new CoinSlot(PermissibleCoins.ONE_EURO, 100);
		CoinSlot cent200 = new CoinSlot(PermissibleCoins.TWO_EUROS, 10);

		coinStore = new HashMap<PermissibleCoins, CoinSlot>();
		coinStore.put(cent10.getDenomination(), cent10);
		coinStore.put(cent20.getDenomination(), cent20);
		coinStore.put(cent50.getDenomination(), cent50);
		coinStore.put(cent100.getDenomination(), cent100);
		coinStore.put(cent200.getDenomination(), cent200);

	}

}
