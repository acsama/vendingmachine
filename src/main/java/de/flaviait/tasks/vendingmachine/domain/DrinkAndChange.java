package de.flaviait.tasks.vendingmachine.domain;

import java.util.Arrays;

/**
 * @author <a href="mailto:acsama@gmail.com.de">Achiri Sama Teyha</a>
 */
public class DrinkAndChange {

	Drink drink;
	Coin[] Change;

	/**
	 * full constructor
	 * 
	 * @param drink
	 * @param change
	 */
	public DrinkAndChange(Drink drink, Coin... change) {
		super();
		this.drink = drink;
		Change = change;
	}

	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	public Coin[] getChange() {
		return Change;
	}

	public void setChange(Coin[] change) {
		Change = change;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(Change);
		result = prime * result + ((drink == null) ? 0 : drink.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DrinkAndChange other = (DrinkAndChange) obj;
		if (!Arrays.equals(Change, other.Change))
			return false;
		if (drink == null) {
			if (other.drink != null)
				return false;
		} else if (!drink.equals(other.drink))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DrinkAndChange [drink=" + drink + ", Change="
				+ Arrays.toString(Change) + "]";
	}

	

}
