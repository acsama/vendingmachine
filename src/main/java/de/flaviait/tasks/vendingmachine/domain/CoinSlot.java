package de.flaviait.tasks.vendingmachine.domain;

public class CoinSlot {

	PermissibleCoins denomination;
	int count;

	public CoinSlot(PermissibleCoins denomination, int count) {
		super();
		this.denomination = denomination;
		this.count = count;
	}
	

	public CoinSlot(PermissibleCoins denomination) {
		super();
		this.denomination = denomination;
	}

	public PermissibleCoins getDenomination() {
		return denomination;
	}

	public void setDenomination(PermissibleCoins denomination) {
		this.denomination = denomination;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void increaseCount() {
		this.count++;
	}

	public void reduceCount() {
		this.count--;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
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
		CoinSlot other = (CoinSlot) obj;
		if (count != other.count)
			return false;
		if (denomination != other.denomination)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "CoinSlot [denomination=" + denomination + ", count=" + count
				+ "]";
	}

	

}
