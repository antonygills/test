package com.cba.simulator.exception;

/**
 * Class for creating all Business related exceptions.
 * 
 * @author Gills Antony
 */
public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;

	private final String customErrorMessage;

	public BusinessException(String message) {
		this.customErrorMessage = message;
	}

	public BusinessException(Throwable cause, String message) {
		super(cause);
		this.customErrorMessage = message;
	}

	@Override
	public String getMessage() {
		return this.customErrorMessage;
	}
}
