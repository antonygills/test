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
