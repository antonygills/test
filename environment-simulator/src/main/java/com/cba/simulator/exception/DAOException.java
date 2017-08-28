package com.cba.simulator.exception;

/**
 * Class for creating all DAO related exceptions.
 *
 * @author Gills Antony
 */
public class DAOException extends Exception {
	private static final long serialVersionUID = 1L;

	private final String customErrorMessage;

	public DAOException(String message) {
		this.customErrorMessage = message;
	}

	public DAOException(String message, Throwable cause) {
		super(cause);
		this.customErrorMessage = message;
	}

	@Override
	public String getMessage() {
		return this.customErrorMessage;
	}
}
