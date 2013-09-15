package de.flaviait.tasks.vendingmachine.domain;

/**
 * 
 * @author <a href="mailto:acsama@gmail.com.de">Achiri Sama Teyha</a>
 */
public class Coin {

	PermissibleCoins denomination;

	public Coin(PermissibleCoins denomination, int count) {
		super();
		this.denomination = denomination;
	}
	

	public Coin(PermissibleCoins denomination) {
		super();
		this.denomination = denomination;
	}

	public PermissibleCoins getDenomination() {
		return denomination;
	}

	public void setDenomination(PermissibleCoins denomination) {
		this.denomination = denomination;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((denomination == null) ? 0 : denomination.hashCode());
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
		Coin other = (Coin) obj;
		if (denomination != other.denomination)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Coin [denomination=" + denomination + "]";
	}

	

}
