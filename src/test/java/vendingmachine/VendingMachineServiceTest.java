package vendingmachine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.flaviait.tasks.vendingmachine.domain.Coin;
import de.flaviait.tasks.vendingmachine.domain.Drink;
import de.flaviait.tasks.vendingmachine.domain.DrinkAndChange;
import de.flaviait.tasks.vendingmachine.domain.PermissibleCoins;
import de.flaviait.tasks.vendingmachine.exceptions.DrinkUnavailableException;
import de.flaviait.tasks.vendingmachine.exceptions.InsufficientChangeException;
import de.flaviait.tasks.vendingmachine.service.VendingMachineService;
import de.flaviait.tasks.vendingmachine.serviceImpl.VendingMachineServiceImpl;

public class VendingMachineServiceTest {

	private VendingMachineService vendingMachineService;

	@Before
	public void setUp() {
		vendingMachineService = new VendingMachineServiceImpl();
	}

	@Test
	public void testBuy() throws DrinkUnavailableException,
			InsufficientChangeException {
		Drink selelctedDrink = new Drink(101L);
		Coin[] paidInCoin = { new Coin(PermissibleCoins.CENT50),
				new Coin(PermissibleCoins.CENT50),
				new Coin(PermissibleCoins.CENT50) };
		DrinkAndChange drinkAndChange = vendingMachineService.buy(
				selelctedDrink, paidInCoin);

		Drink boughtDrink = drinkAndChange.getDrink();
		Coin[] change = drinkAndChange.getChange();

		Assert.assertTrue("coke".equalsIgnoreCase(boughtDrink.getName()));
		Assert.assertTrue(change.length == 2);

		int changeValue = 0;

		for (Coin coin : change) {
			changeValue = changeValue
					+ coin.getDenomination().getValue().intValue();
		}
		
		Assert.assertTrue(changeValue == 30);
	}

}
