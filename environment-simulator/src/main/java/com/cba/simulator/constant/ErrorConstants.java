/******************************************************
* Copyright (c) August 2017, Gills Antony.
* All Rights Reserved
* 
* This file can be redistributed and/or modified,
* under the terms of the GNU General Public License
* as published by the Free Software Foundation,
* either version 3 of the License, or any later version.
*******************************************************/
package com.cba.simulator.constant;

import java.io.IOException;

import com.cba.simulator.exception.UtilsException;

/**
 * contains all error messages used in this application
 *
 * @author Gills Antony
 */
public class ErrorConstants {

	public static final String DAO_MYBATIS_FETCH_FAILED = "Unable to fetch Data from Database.";

	public static final String UTILS_PROP_FILE_NOT_FOUND = "Unable to find the external property file.";
	public static final String UTILS_PROP_FILE_LOAD_FAILED = "Unable to load the external property file.";
	public static final String UTILS_PROP_FILE_READ_FAILED = "Exception while reading external property file.";
	
	public static final String UTILS_OPERATION_FAILED = "Unable to perform util operation.";
	public static final String UTILS_PARSE_FAILED = "Unable to parse the Data.";
	public static final String UTILS_FILE_CLOSE_FAILED = "Unable to flush/close file resources.";
	public static final String UTILS_FILE_WRITE_FAILED = "Unable to write data to file.";
	public static final String UTILS_UNABLE_FIND_FILE_PATH = "Unable to find the specified path/file.";
	

	public static final String BUSINESS_INVALIDARGS = "Invalid input arguments";
	public static final String BUSINESS_INVALIDARGS_PASTDATE = "Please enter future date";
	public static final String BUSINESS_INVALIDARGS_ENDDATE = "Please enter correct end date";
	public static final String BUSINESS_INVALIDARGS_INPUTRANGE = "Please select range of dates within one year";
	
	

	private ErrorConstants() {
	}
}
