package de.flaviait.tasks.vendingmachine.domain;

import java.math.BigDecimal;

public class DrinkSlot {

	Long slotNumber;
	String name;
	int count;
	BigDecimal price;

	public DrinkSlot(Long slotNumber, String name, int count, BigDecimal price) {
		super();
		this.slotNumber = slotNumber;
		this.name = name;
		this.count = count;
		this.price = price;
	}

	public Long getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(Long slotNumber) {
		this.slotNumber = slotNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * reduce count by one
	 */
	public void decreaseCount() {
		count--;
	}

	/**
	 * increase count be one
	 */
	public void increaseCount() {
		count++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((slotNumber == null) ? 0 : slotNumber.hashCode());
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
		DrinkSlot other = (DrinkSlot) obj;
		if (count != other.count)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (slotNumber == null) {
			if (other.slotNumber != null)
				return false;
		} else if (!slotNumber.equals(other.slotNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DrinkSlot [slotNumber=" + slotNumber + ", name=" + name
				+ ", count=" + count + ", price=" + price + "]";
	}

}
