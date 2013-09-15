package de.flaviait.tasks.vendingmachine.domain;

import java.math.BigDecimal;

/**
 * 
 * @author <a href="mailto:acsama@gmail.com.de">Achiri Sama Teyha</a>
 */
public enum PermissibleCoins {

	CENT10("10 cent coin", BigDecimal.valueOf(10.0)), CENT20("20 cent coin",
			BigDecimal.valueOf(20.0)), CENT50("50 cent coin", BigDecimal
			.valueOf(50.0)), ONE_EURO("1 euro coin", BigDecimal.valueOf(100.0)), TWO_EUROS(
			"2 euro coin", BigDecimal.valueOf(200.0));

	private final String name;
	private final BigDecimal value;

	PermissibleCoins(String name, BigDecimal value) {
		this.name = name;
		this.value = value;

	}

	public String getName() {
		return this.name;
	}

	public BigDecimal getValue() {
		return value;
	}
}
