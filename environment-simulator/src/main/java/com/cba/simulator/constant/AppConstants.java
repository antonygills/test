package com.cba.simulator.constant;

/**
 * contains all Constants used in this application
 *
 * @author Gills Antony
 */
public class AppConstants {
	public static final String OUTPUT_FILE_PATH = "src/main/resources/output/ForecastedData.txt";

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

	private AppConstants() {
	}
}
