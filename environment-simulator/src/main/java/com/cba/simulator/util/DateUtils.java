package com.cba.simulator.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.cba.simulator.constant.AppConstants;
import com.cba.simulator.constant.ErrorConstants;
import com.cba.simulator.exception.UtilsException;

/**
 * Util class containing common date functions.
 * 
 * @author Gills Antony
 */
public class DateUtils {

	/**
	 * Returns formatted Date object which is the formatted date of the input
	 * string.
	 * 
	 * @param dateString
	 *            date in string format
	 * @return formatted Date
	 * @throws UtilsException
	 */
	public static Date getFormattedDateFromString(final String dateString) throws UtilsException {
		SimpleDateFormat sdf = new SimpleDateFormat();
		Date formattedDate = null;
		try {
			sdf.applyPattern(AppConstants.PATTERN_DATE);
			formattedDate = sdf.parse(dateString);
		} catch (ParseException e) {
			throw new UtilsException(ErrorConstants.UTILS_PARSE_FAILED, e);
		} catch (Exception e) {
			throw new UtilsException("Unable to format Date", e);
		}
		return formattedDate;
	}

	/**
	 * Returns formatted Date object which is the formatted date of the input Date
	 * object.
	 * 
	 * @param dateInstance
	 * @return
	 * @throws UtilsException
	 */
	public static Date getFormattedDate(final Date dateInstance) throws UtilsException {
		SimpleDateFormat sdf = new SimpleDateFormat();
		Date outputDate = null;
		try {
			sdf.applyPattern(AppConstants.PATTERN_DATE_TIME);
			String formattedDate = sdf.format(dateInstance);
			outputDate = sdf.parse(formattedDate);
		} catch (ParseException e) {
			throw new UtilsException(ErrorConstants.UTILS_PARSE_FAILED, e);
		} catch (Exception e) {
			throw new UtilsException("Unable to format Date", e);
		}
		return outputDate;
	}

	/**
	 * Returns formatted date-time from the input Date.
	 * 
	 * @param dateInstance
	 * @return
	 */
	public static String getFormattedeDateTime(final Date dateInstance) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(AppConstants.PATTERN_DATE_TIME);
		return sdf.format(dateInstance);
	}

	/**
	 * Returns the day of the year of the input date.
	 * 
	 * @param dateInstance
	 * @return day number of the input year
	 */
	public static int getDayOfYear(final Date dateInstance) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(dateInstance);
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * Returns the Date from the DayOfYear.
	 * 
	 * @param dayOfYear
	 * @return
	 */
	public static Date getDateFromDayOfYear(final int dayOfYear, final int year) {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.DAY_OF_YEAR, dayOfYear);
		cal.set(Calendar.YEAR, year);
		return cal.getTime();
	}

	/**
	 * Method to extract the year from Date.
	 * 
	 * @param dateInstance
	 *            date instance in Date format
	 * @return
	 */
	public static int getYearFromDate(Date dateInstance) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(AppConstants.PATTERN_YEAR);
		return Integer.parseInt(sdf.format(dateInstance));
	}

	/**
	 * Return a date after adding or subtracting number of days to input date.
	 * 
	 * @param dateInstance
	 * @param numberOfDaysToAdd
	 * @return
	 */
	public static Date addDayToDate(final Date dateInstance, final int numberOfDaysToAdd) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(dateInstance);
		cal.add(Calendar.DATE, numberOfDaysToAdd);
		return cal.getTime();
	}

	private DateUtils() {
	}

}
