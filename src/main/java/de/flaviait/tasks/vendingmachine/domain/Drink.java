package de.flaviait.tasks.vendingmachine.domain;


public class Drink {

	Long slotNumber;
	String name;

	public Drink(Long slotNumber, String name) {
		super();
		this.slotNumber = slotNumber;
		this.name = name;

	}

	public Drink(Long slotNumber) {
		super();
		this.slotNumber = slotNumber;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Drink other = (Drink) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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
		return "Drink [slotNumber=" + slotNumber + ", name=" + name + "]";
	}

}
