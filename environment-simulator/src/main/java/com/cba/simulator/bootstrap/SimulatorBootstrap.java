package com.cba.simulator.bootstrap;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.omg.CORBA.portable.ApplicationException;

import com.cba.simulator.constant.AppConstants;
import com.cba.simulator.constant.ErrorConstants;
import com.cba.simulator.dataobject.SimulatorInputDTO;
import com.cba.simulator.exception.BusinessException;
import com.cba.simulator.exception.UtilsException;
import com.cba.simulator.simulator.SimulatorController;
import com.cba.simulator.util.DateUtils;
import com.cba.simulator.util.Utils;

/**
 * Acts as triggering point of SimulatorBootstrap.
 * </p>
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
	 * Main Method - Entry point method to SimulatorBootstrap
	 * 
	 * @param args
	 * @throws UtilsException
	 * @throws BusinessException
	 */
	public static void main(String[] args) {
		LOGGER.debug("Enter SimulatorBootstrap.");
		SimulatorController simulatorController = new SimulatorController();
		try {
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
	 *            maximum of two arguments. All arguments should be date.
	 * @throws BusinessException
	 * @throws UtilsException
	 */
	public static boolean validateInputArgs(final String[] args) throws BusinessException, UtilsException {
		try {
			Date startDate = null;
			Date endDate = null;
			Date date = null;
			if (args.length == 1 || args.length == 2) {
				startDate = DateUtils.getFormattedDateFromString(args[0]);
				date = new Date();
				if (startDate.before(DateUtils.getFormattedDate(date))) {
					throw new BusinessException(ErrorConstants.BUSINESS_INVALIDARGS_PASTDATE);
				}
			}
			if (args.length == 2) {
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
	 *            maximum of two arguments. All arguments should be date.
	 * @return
	 * @throws UtilsException
	 */
	public static SimulatorInputDTO processInputArgs(final String[] args) throws UtilsException {
		SimulatorInputDTO simulatorInputDTO = new SimulatorInputDTO();
		try {
			if (args.length == 0) {
				simulatorInputDTO.setInputOperation(AppConstants.PREDICT_DEFAULT_ONE_WEEK);
				
				Date startDate = DateUtils.getFutureDate(new Date(), 1);
				simulatorInputDTO.setStartDate(startDate);
				simulatorInputDTO.setStartDayOfYear(DateUtils.getDayOfYear(startDate));
				
				Date endDate = DateUtils.getFutureDate(startDate, AppConstants.NUMBER_OF_DAYS_FOR_WEEK);
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
	 * @param batchInputVO
	 * @param e
	 */
	private static void printExceptionStackTrace(Exception e) {
		LOGGER.error(AppConstants.ERROR_STACK_LOG_PATTERN + ExceptionUtils.getStackTrace(e));
	}

}
