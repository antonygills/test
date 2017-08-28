/******************************************************
* Copyright (c) August 2017, Gills Antony.
* All Rights Reserved
* 
* This file can be redistributed and/or modified,
* under the terms of the GNU General Public License
* as published by the Free Software Foundation,
* either version 3 of the License, or any later version.
*******************************************************/
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
