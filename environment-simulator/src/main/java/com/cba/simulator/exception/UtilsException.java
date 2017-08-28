package com.cba.simulator.exception;

/**
 * Class for creating all Util related exceptions.
 *
 * @author Gills Antony
 */
public class UtilsException extends Exception {
	private static final long serialVersionUID = 1L;

	private final String customErrorMessage;

	public UtilsException(String message) {
		this.customErrorMessage = message;
	}

	public UtilsException(String message, Throwable cause) {
		super(cause);
		this.customErrorMessage = message;
	}

	@Override
	public String getMessage() {
		return this.customErrorMessage;
	}
}
