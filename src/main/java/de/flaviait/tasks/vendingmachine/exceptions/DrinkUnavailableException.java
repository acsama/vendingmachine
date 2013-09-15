package de.flaviait.tasks.vendingmachine.exceptions;

/**
 * 
 * @author <a href="mailto:acsama@gmail.com.de">Achiri Sama Teyha</a>
 */
public class DrinkUnavailableException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DrinkUnavailableException() {
		super();
	}

	/**
	 * @param message
	 */
	public DrinkUnavailableException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DrinkUnavailableException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DrinkUnavailableException(String message, Throwable cause) {
		super(message, cause);
	}
}
