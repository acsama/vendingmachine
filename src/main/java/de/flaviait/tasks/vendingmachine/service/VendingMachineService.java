package de.flaviait.tasks.vendingmachine.service;

import de.flaviait.tasks.vendingmachine.domain.Coin;
import de.flaviait.tasks.vendingmachine.domain.Drink;
import de.flaviait.tasks.vendingmachine.domain.DrinkAndChange;
import de.flaviait.tasks.vendingmachine.exceptions.DrinkUnavailableException;
import de.flaviait.tasks.vendingmachine.exceptions.InsufficientChangeException;

/**
 * 
 * @author <a href="mailto:achiri.sama@jungdms.de">Achiri Sama Teyha</a>
 * 
 *         WoF 2
 * 
 *         Copyright &copy; <a href="http://www.jungdms.de">Jung, DMS & CIE
 *         AG</a> 2013
 * 
 *         All Rights Reserved.
 */
public interface VendingMachineService {

	/**
	 * 
	 * @param choice
	 * @param input
	 * @return
	 * @throws DrinkUnavailableException
	 * @throws InsufficientChangeException
	 */
	public DrinkAndChange buy(Drink selectedDrink, Coin... input)
			throws InsufficientChangeException,DrinkUnavailableException ;

	/**
	 * 
	 * @param drink
	 */
	public void replenishDrinks(Drink drink);

	/**
	 * 
	 * @param change
	 */
	public void replenishChange(Coin change);

	/**
	 * 
	 */
	public void emptyDrinkStore();

	/**
	 * 
	 */
	public void emptyChangeStore();

}
