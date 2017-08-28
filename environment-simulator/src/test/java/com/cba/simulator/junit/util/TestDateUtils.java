/******************************************************
* Copyright (c) August 2017, Gills Antony.
* All Rights Reserved
* 
* This file can be redistributed and/or modified,
* under the terms of the GNU General Public License
* as published by the Free Software Foundation,
* either version 3 of the License, or any later version.
*******************************************************/
package com.cba.simulator.junit.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cba.simulator.constant.AppConstants;
import com.cba.simulator.exception.UtilsException;
import com.cba.simulator.util.DateUtils;

public class TestDateUtils {
	private static Date inputDateInstance;
	private static Date inputDateTimeInstance;
	private static String inputDateString;

	@BeforeClass
	public static void setUpOnce() throws ParseException {
		inputDateString = "2017-01-10 10:30:05";
		SimpleDateFormat sdf_date = new SimpleDateFormat(AppConstants.PATTERN_DATE_TIME);
		inputDateTimeInstance = sdf_date.parse(inputDateString);
		SimpleDateFormat sdf_dateTime = new SimpleDateFormat(AppConstants.PATTERN_DATE);
		inputDateInstance = sdf_dateTime.parse(inputDateString);
	}

	@Test
	public void testGetFormattedDateFromString() throws UtilsException {
		assertEquals(inputDateInstance, DateUtils.getFormattedDateFromString(inputDateString));
	}

	@Test
	public void testGetFormattedDate() throws UtilsException {
		SimpleDateFormat sdf = new SimpleDateFormat(AppConstants.PATTERN_DATE_TIME);
		assertEquals(inputDateString, sdf.format(DateUtils.getFormattedDate(inputDateTimeInstance)));
	}

	@Test
	public void testGetFormattedeDateTime() {
		assertEquals(inputDateString, DateUtils.getFormattedeDateTime(inputDateTimeInstance));
	}

	@Test
	public void testGetDayOfYear() {
		assertEquals(10, DateUtils.getDayOfYear(inputDateTimeInstance));
	}

	@Test
	public void testGetDateFromDayOfYear() {
		Date currentDate = new Date();
		Calendar cal = new GregorianCalendar();
		int dayOfYearToday = cal.get(Calendar.DAY_OF_YEAR);
		int currentYear = cal.get(Calendar.YEAR);
		SimpleDateFormat sdf = new SimpleDateFormat(AppConstants.PATTERN_DATE_TIME);

		assertEquals(sdf.format(currentDate), sdf.format(DateUtils.getDateFromDayOfYear(dayOfYearToday, currentYear)));
	}

	@Test
	public void testGetYearFromDate() {
		assertEquals(2017, DateUtils.getYearFromDate(inputDateInstance));
	}
}
