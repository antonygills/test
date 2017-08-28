package com.cba.simulator.constant;

/**
 * contains all error messages used in this application
 *
 * @author Gills Antony
 */
public class ErrorConstants {

	public static final String DAO_MYBATIS_FETCH_FAILED = "Unable to fetch Data from Database.";

	public static final String UTILS_OPERATION_FAILED = "Unable to perform util operation.";
	public static final String UTILS_PARSE_FAILED = "Unable to parse the Data.";
	public static final String UTILS_FILE_CLOSE_FAILED = "Unable to flush/close file resources.";
	public static final String UTILS_FILE_WRITE_FAILED = "Unable to write data to file.";

	public static final String BUSINESS_INVALIDARGS = "Invalid input arguments";
	public static final String BUSINESS_INVALIDARGS_PASTDATE = "Please enter future date";
	public static final String BUSINESS_INVALIDARGS_ENDDATE = "Please enter correct end date";
	public static final String BUSINESS_INVALIDARGS_INPUTRANGE = "Please select range of dates within one year";

	private ErrorConstants() {
	}
}
