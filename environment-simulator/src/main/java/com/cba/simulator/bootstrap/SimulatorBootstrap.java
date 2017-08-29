/******************************************************
* Copyright (c) August 2017, Gills Antony.
* All Rights Reserved
* 
* This file can be redistributed and/or modified,
* under the terms of the GNU General Public License
* as published by the Free Software Foundation,
* either version 3 of the License, or any later version.
*******************************************************/
package com.cba.simulator.bootstrap;

import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.cba.simulator.constant.AppConstants;
import com.cba.simulator.constant.ErrorConstants;
import com.cba.simulator.controller.SimulatorController;
import com.cba.simulator.dataobject.SimulatorInputDTO;
import com.cba.simulator.exception.BusinessException;
import com.cba.simulator.exception.UtilsException;
import com.cba.simulator.util.DateUtils;
import com.cba.simulator.util.FileUtils;

/**
 * Acts as triggering point of SimulatorBootstrap.
 * <p>
 * This class is responsible for the following,
 * <ul>
 * <li>validate the input arguments.</li>
 * <li>process the input arguments and set the values to input object.</li>
 * </ul>
 * 
 * @author Gills Antony
 */
public class SimulatorBootstrap {

	private static final Logger LOGGER = Logger.getLogger(SimulatorBootstrap.class);

	/**
	 * Main Method - Entry point method to SimulatorBootstrap.
	 * <p>
	 * Accepts user input arguments as follows,
	 * <ul>
	 * <li>No arguments - simulate future environment data for 1 week from
	 * today.</li>
	 * <li>One Date argument (Date in "yyyy-MM-dd" format) - simulate future
	 * environment data for the input date.</li>
	 * <li>Two Date arguments (Date in "yyyy-MM-dd" format) - simulate future
	 * environment data for the input date range.</li>
	 * </ul>
	 * 
	 * @param args
	 *            - user input arguments
	 */
	public static void main(String[] args) {
		LOGGER.debug("Enter SimulatorBootstrap.");
		SimulatorController simulatorController = new SimulatorController();
		try {
			AppConstants.loadExternalProperties(AppConstants.EXTERNAL_PROPERTY_FILE);
			validateInputArgs(args);
			SimulatorInputDTO simulatorInputDTO = processInputArgs(args);
			simulatorController.startExecution(simulatorInputDTO);
		} catch (Exception e) {
			printExceptionStackTrace(e);
			System.exit(1);
		}
		LOGGER.debug("Exit gracefully after completing execution.");
	}

	/**
	 * method to validate input arguments
	 * 
	 * @param args
	 *            - user input arguments
	 * @return true if validation is successful
	 * @throws BusinessException
	 *             if input arguments are not correct.
	 * @throws UtilsException
	 *             if util function fails.
	 */
	public static boolean validateInputArgs(final String[] args) throws BusinessException, UtilsException {
		Date startDate = null;
		Date endDate = null;
		Date date = null;
		try {
			if (args.length == 1 || args.length == 2) {
				if (!DateUtils.validateDatePattern(args[0])) {
					throw new BusinessException(ErrorConstants.BUSINESS_INVALIDARGS);
				}

				startDate = DateUtils.getFormattedDateFromString(args[0]);
				date = DateUtils.addDayToDate(new Date(), -1);
				if (startDate.before(DateUtils.getFormattedDate(date))) {
					throw new BusinessException(ErrorConstants.BUSINESS_INVALIDARGS_PASTDATE);
				}
			}

			if (args.length == 2) {
				if (!DateUtils.validateDatePattern(args[1])) {
					throw new BusinessException(ErrorConstants.BUSINESS_INVALIDARGS);
				}

				endDate = DateUtils.getFormattedDateFromString(args[1]);
				if (endDate == null || endDate.before(startDate)) {
					throw new BusinessException(ErrorConstants.BUSINESS_INVALIDARGS_ENDDATE);
				}
				if ((DateUtils.getYearFromDate(endDate) - DateUtils.getYearFromDate(startDate)) > 1) {
					throw new BusinessException(ErrorConstants.BUSINESS_INVALIDARGS_INPUTRANGE);
				}
			}

			if (args.length > AppConstants.ARGS_MAX_COUNT) {
				throw new BusinessException(ErrorConstants.BUSINESS_INVALIDARGS);
			}
		} catch (UtilsException e) {
			throw new UtilsException(ErrorConstants.UTILS_OPERATION_FAILED, e);
		}
		return true;
	}

	/**
	 * method to process input arguments. Input arguments are processed to form
	 * input data object.
	 * 
	 * @param args
	 *            - maximum of two arguments. All arguments should be date.
	 * @return input object with data based on user input.
	 * @throws UtilsException
	 *             if any Util function fails.
	 */
	public static SimulatorInputDTO processInputArgs(final String[] args) throws UtilsException {
		SimulatorInputDTO simulatorInputDTO = new SimulatorInputDTO();
		try {
			if (args.length == 0) {
				simulatorInputDTO.setInputOperation(AppConstants.PREDICT_DEFAULT_ONE_WEEK);

				Date startDate = DateUtils.addDayToDate(new Date(), 1);
				simulatorInputDTO.setStartDate(startDate);
				simulatorInputDTO.setStartDayOfYear(DateUtils.getDayOfYear(startDate));

				Date endDate = DateUtils.addDayToDate(startDate, AppConstants.NUMBER_OF_DAYS_FOR_WEEK);
				simulatorInputDTO.setEndDate(endDate);
				simulatorInputDTO.setEndDayOfYear(DateUtils.getDayOfYear(endDate));
			}
			if (args.length == 1) {
				simulatorInputDTO.setInputOperation(AppConstants.PREDICT_ONE_DAY);
			}
			if (args.length == 1 || args.length == 2) {
				Date startDate = DateUtils.getFormattedDateFromString(args[0]);
				simulatorInputDTO.setStartDate(startDate);
				simulatorInputDTO.setStartDayOfYear(DateUtils.getDayOfYear(startDate));
			}
			if (args.length == 2) {
				simulatorInputDTO.setInputOperation(AppConstants.PREDICT_FOR_GIVEN_PERIOD);
				Date endDate = DateUtils.getFormattedDateFromString(args[1]);
				simulatorInputDTO.setEndDate(endDate);
				simulatorInputDTO.setEndDayOfYear(DateUtils.getDayOfYear(endDate));
			}
		} catch (UtilsException e) {
			throw new UtilsException(ErrorConstants.UTILS_OPERATION_FAILED, e);
		}
		return simulatorInputDTO;
	}

	/**
	 * method to print all stack trace of Exceptions
	 * 
	 * @param exception
	 *            - Exception
	 */
	private static void printExceptionStackTrace(Exception exception) {
		LOGGER.error(AppConstants.ERROR_STACK_LOG_PATTERN + ExceptionUtils.getStackTrace(exception));
	}

}
