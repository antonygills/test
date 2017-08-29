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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.cba.simulator.exception.UtilsException;

/**
 * contains all Constants used in this application
 *
 * @author Gills Antony
 */
public class AppConstants {

	public static final String EXTERNAL_PROPERTY_FILE = "EnvironmentSimulator.properties";

	public static final String PREDICT_DEFAULT_ONE_WEEK = "DefaultOneWeek";
	public static final String PREDICT_ONE_DAY = "OneDay";
	public static final String PREDICT_FOR_GIVEN_PERIOD = "GivenPeriod";

	public static final String WEATHER_RAIN = "Rain";
	public static final String WEATHER_FOG = "Fog";
	public static final String WEATHER_WARM = "Warm";
	public static final String WEATHER_COOL = "Cool";
	public static final String WEATHER_HOT = "Hot";
	public static final String WEATHER_COLD = "Cold";

	public static final String PATTERN_YEAR = "yyyy";
	public static final String PATTERN_DATE = "yyyy-MM-dd";
	public static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

	public static final int ARGS_MAX_COUNT = 2;
	public static final int NUMBER_OF_DAYS_FOR_WEEK = 7;
	public static final String SPLITLINE_PATTERN = "||";

	public static final int SUCCESS = 1;
	public static final int FAILURE = 0;
	public static final String ERROR_STACK_LOG_PATTERN = "Error Stacktrace ::";

	public static Properties prop;

	public static String OUTPUT_FILE = null;
	public static String DATABASE_DRIVER = null;
	public static String DATABASE_URL = null;
	public static String DATABASE_USERNAME = null;
	public static String DATABASE_PASSWORD = null;

	/**
	 * 
	 * Method to load external property file
	 * 
	 * @param propertiesFile
	 *            property file name.
	 * @throws UtilsException
	 *             If unable to load/read property file.
	 */
	public static void loadExternalProperties(String propertiesFile) throws UtilsException {
		try {
			prop = new Properties();
			prop.load(new FileInputStream(propertiesFile));

			OUTPUT_FILE = prop.getProperty("output.file");
			DATABASE_DRIVER = prop.getProperty("db.mysql.driver");
			DATABASE_URL = prop.getProperty("db.mysql.url");
			DATABASE_USERNAME = prop.getProperty("db.mysql.username");
			DATABASE_PASSWORD = prop.getProperty("db.mysql.password");

		} catch (FileNotFoundException e) {
			throw new UtilsException(ErrorConstants.UTILS_UNABLE_FIND_FILE_PATH);
		} catch (IOException e) {
			throw new UtilsException(ErrorConstants.UTILS_PROP_FILE_LOAD_FAILED);
		} catch (Exception e) {
			throw new UtilsException(ErrorConstants.UTILS_PROP_FILE_READ_FAILED);
		}

	}

	private AppConstants() {
	}
}
