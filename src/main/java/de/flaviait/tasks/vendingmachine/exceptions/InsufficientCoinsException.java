package de.flaviait.tasks.vendingmachine.exceptions;

/**
 * 
 * @author <a href="mailto:acsama@gmail.com.de">Achiri Sama Teyha</a>
 * 
 */
public class InsufficientCoinsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientCoinsException() {
	}

	public InsufficientCoinsException(String message) {
		super(message);
	}

	public InsufficientCoinsException(Throwable cause) {
		super(cause);
	}

	public InsufficientCoinsException(String message, Throwable cause) {
		super(message, cause);
	}

	public InsufficientCoinsException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
