package vendingmachine;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.flaviait.tasks.vendingmachine.domain.Coin;
import de.flaviait.tasks.vendingmachine.domain.Drink;
import de.flaviait.tasks.vendingmachine.domain.DrinkAndChange;
import de.flaviait.tasks.vendingmachine.domain.PermissibleCoins;
import de.flaviait.tasks.vendingmachine.exceptions.DrinkUnavailableException;
import de.flaviait.tasks.vendingmachine.exceptions.ChangeUnvailableException;
import de.flaviait.tasks.vendingmachine.exceptions.InsufficientCoinsException;
import de.flaviait.tasks.vendingmachine.service.VendingMachine;
import de.flaviait.tasks.vendingmachine.serviceImpl.VendingMachineImpl;

/**
 * 
 * @author <a href="mailto:acsama@gmail.com.de">Achiri Sama Teyha</a>
 * 
 */
public class VendingMachineServiceTest {

	private VendingMachine vendingMachine;

	@Before
	public void setUp() {
		vendingMachine = new VendingMachineImpl();
	}

	@Test
	public void testBuy() throws DrinkUnavailableException,
			ChangeUnvailableException, InsufficientCoinsException {

		Drink selelctedDrink = new Drink(101L);

		Coin[] paidInCoin = { new Coin(PermissibleCoins.ONE_EURO),
				new Coin(PermissibleCoins.CENT50) };

		int oneEuroCountExpectedAfterBuy = vendingMachine
				.getCoinInventory(new Coin(PermissibleCoins.ONE_EURO)) + 1;

		int cent50CountExpectedAfterBuy = vendingMachine
				.getCoinInventory(new Coin(PermissibleCoins.CENT50)) + 1;

		int cokeDrinkCountExpectedAfterBuy = vendingMachine
				.getDrinkInventory(selelctedDrink) - 1;

		DrinkAndChange drinkAndChange = vendingMachine.buy(selelctedDrink,
				paidInCoin);

		Drink boughtDrink = drinkAndChange.getDrink();

		Coin[] change = drinkAndChange.getChange();

		Assert.assertTrue("coke".equalsIgnoreCase(boughtDrink.getName()));
		Assert.assertTrue(change.length == 2);

		BigDecimal changeValue = BigDecimal.ZERO;

		for (Coin coin : change) {
			changeValue = changeValue.add(coin.getDenomination().getValue());
		}

		Assert.assertTrue(BigDecimal.valueOf(30.0).equals(changeValue));

		int oneEuroCountAfterBuy = vendingMachine.getCoinInventory(new Coin(
				PermissibleCoins.ONE_EURO));

		int cent50CountAfterBuy = vendingMachine.getCoinInventory(new Coin(
				PermissibleCoins.CENT50));

		int cokeDrinkAfterBuy = vendingMachine
				.getDrinkInventory(selelctedDrink);

		Assert.assertTrue("expected " + oneEuroCountExpectedAfterBuy
				+ "   but got " + oneEuroCountAfterBuy,
				oneEuroCountAfterBuy == oneEuroCountExpectedAfterBuy);

		Assert.assertTrue("expected " + cent50CountExpectedAfterBuy
				+ "   but got " + cent50CountAfterBuy,
				cent50CountAfterBuy == cent50CountExpectedAfterBuy);

		Assert.assertTrue("expected " + cokeDrinkCountExpectedAfterBuy
				+ "   but got " + cokeDrinkAfterBuy,
				cokeDrinkAfterBuy == cokeDrinkCountExpectedAfterBuy);
	}

	@Test(expected = DrinkUnavailableException.class)
	public void testDrinkUnavailableException()
			throws ChangeUnvailableException, DrinkUnavailableException,
			InsufficientCoinsException {

		Drink selelctedDrink = new Drink(101L);

		Coin[] paidInCoin = { new Coin(PermissibleCoins.CENT50),
				new Coin(PermissibleCoins.CENT50),
				new Coin(PermissibleCoins.CENT50) };

		vendingMachine.emptyDrinkStore();

		vendingMachine.buy(selelctedDrink, paidInCoin);
	}

	@Test(expected = ChangeUnvailableException.class)
	public void testInsufficientChangeException()
			throws ChangeUnvailableException, DrinkUnavailableException,
			InsufficientCoinsException {

		Drink selelctedDrink = new Drink(101L);

		Coin[] paidInCoin = { new Coin(PermissibleCoins.CENT50),
				new Coin(PermissibleCoins.CENT50),
				new Coin(PermissibleCoins.CENT50) };

		vendingMachine.emptyChangeStore();

		vendingMachine.buy(selelctedDrink, paidInCoin);
	}

	@Test(expected = InsufficientCoinsException.class)
	public void testInsufficientCoinsException()
			throws ChangeUnvailableException, DrinkUnavailableException,
			InsufficientCoinsException {

		Drink selelctedDrink = new Drink(101L);

		Coin[] paidInCoin = { new Coin(PermissibleCoins.CENT50),
				new Coin(PermissibleCoins.CENT50) };

		vendingMachine.buy(selelctedDrink, paidInCoin);

	}

	@Test
	public void testDrinkReplenishMent() {

		Drink newDrink = new Drink(201L);

		int expectedCountAfterReplenishment = vendingMachine
				.getDrinkInventory(newDrink) + 1;

		vendingMachine.replenishDrinks(newDrink);

		int actualCountAfterReplenishment = vendingMachine
				.getDrinkInventory(newDrink);

		Assert.assertTrue(
				"expected " + expectedCountAfterReplenishment + " but got "
						+ actualCountAfterReplenishment,
				actualCountAfterReplenishment == expectedCountAfterReplenishment);

	}

	@Test
	public void testCoinReplenishMent() {

		Coin newCoin = new Coin(PermissibleCoins.CENT50);

		int expectedCountAfterReplenishment = vendingMachine
				.getCoinInventory(newCoin) + 1;

		vendingMachine.replenishChange(newCoin);

		int actualCountAfterReplenishment = vendingMachine
				.getCoinInventory(newCoin);

		Assert.assertTrue(
				"expected " + expectedCountAfterReplenishment + " but got "
						+ actualCountAfterReplenishment,
				actualCountAfterReplenishment == expectedCountAfterReplenishment);

	}

}
