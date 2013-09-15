package de.flaviait.tasks.vendingmachine.service;

import de.flaviait.tasks.vendingmachine.domain.Coin;
import de.flaviait.tasks.vendingmachine.domain.Drink;
import de.flaviait.tasks.vendingmachine.domain.DrinkAndChange;
import de.flaviait.tasks.vendingmachine.exceptions.DrinkUnavailableException;
import de.flaviait.tasks.vendingmachine.exceptions.ChangeUnvailableException;
import de.flaviait.tasks.vendingmachine.exceptions.InsufficientCoinsException;

/**
 * 
 * @author <a href="mailto:acsama@gmail.com.de">Achiri Sama Teyha</a>
 * 
 */
public interface VendingMachine {

	/**
	 * this method would return the selected drink and the balance if there is
	 * any.
	 * 
	 * @param selectedDrink
	 * @param paidInCoins
	 * @return {@link DrinkAndChange}
	 * @throws ChangeUnvailableException
	 * @throws DrinkUnavailableException
	 * @throws InsufficientCoinsException TODO
	 */
	public DrinkAndChange buy(Drink selectedDrink, Coin... paidInCoins)
			throws ChangeUnvailableException, DrinkUnavailableException, InsufficientCoinsException;

	/**
	 * replenishes the drink stock of the given drink.
	 * 
	 * @param drink
	 */
	public void replenishDrinks(Drink drink);

	/**
	 * replenish change stock for the given change
	 * 
	 * @param change
	 */
	public void replenishChange(Coin change);

	/**
	 * empties the drink store
	 */
	public void emptyDrinkStore();

	/**
	 * empties the change store
	 */
	public void emptyChangeStore();

	/**
	 * 
	 * @param coin
	 * @return how many of the given coins are in stock
	 */
	public int getCoinInventory(Coin coin);

	/**
	 * @param drink
	 * @return how many of the given drinks are in stock
	 */
	public int getDrinkInventory(Drink drink);

}
