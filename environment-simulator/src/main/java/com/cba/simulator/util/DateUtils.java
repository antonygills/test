/******************************************************
* Copyright (c) August 2017, Gills Antony.
* All Rights Reserved
* 
* This file can be redistributed and/or modified,
* under the terms of the GNU General Public License
* as published by the Free Software Foundation,
* either version 3 of the License, or any later version.
*******************************************************/
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
	 *            - date in string format
	 * @return formatted Date
	 * @throws UtilsException
	 *             if the specified string cannot be formatted to Date.
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
	 * @param dateInput
	 *            - date in Date format.
	 * @return formatted date in Date format.
	 * @throws UtilsException
	 *             if the specified date cannot be formatted.
	 */
	public static Date getFormattedDate(final Date dateInput) throws UtilsException {
		SimpleDateFormat sdf = new SimpleDateFormat();
		Date outputDate = null;
		try {
			sdf.applyPattern(AppConstants.PATTERN_DATE_TIME);
			String formattedDate = sdf.format(dateInput);
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
	 * @param dateInput
	 *            - date in Date format.
	 * @return formatted Date-Time as String.
	 */
	public static String getFormattedeDateTime(final Date dateInput) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(AppConstants.PATTERN_DATE_TIME);
		return sdf.format(dateInput);
	}

	/**
	 * Returns the day of the year of the input date.
	 * 
	 * @param dateInput
	 *            date input.
	 * @return day number of the input year.
	 */
	public static int getDayOfYear(final Date dateInput) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(dateInput);
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * Returns the Date from the DayOfYear.
	 * 
	 * @param dayOfYear
	 *            - Day of Year.
	 * @param year
	 *            - Year under consideration.
	 * @return Date.
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
	 * @param dateInput
	 *            - date instance in Date format.
	 * @return Year.
	 */
	public static int getYearFromDate(Date dateInput) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(AppConstants.PATTERN_YEAR);
		return Integer.parseInt(sdf.format(dateInput));
	}

	/**
	 * Return a date after adding or subtracting number of days to input date.
	 * 
	 * @param dateInput
	 *            - Date input.
	 * @param numberOfDaysToAdd
	 *            - number of Days to be added to input Date. Negative value for
	 *            subtraction.
	 * @return Date after adding day.
	 */
	public static Date addDayToDate(final Date dateInput, final int numberOfDaysToAdd) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(dateInput);
		cal.add(Calendar.DATE, numberOfDaysToAdd);
		return cal.getTime();
	}

	/**
	 * validate a date string for the format "yyyy-MM-dd"
	 * 
	 * @param dateInput
	 *            - date string.
	 * @return true if validation success, else false.
	 */
	public static boolean validateDatePattern(final String dateInput) {
		String datePattern = "\\d{4}-\\d{2}-\\d{2}";
		return dateInput.matches(datePattern);
	}

	private DateUtils() {
	}

}
