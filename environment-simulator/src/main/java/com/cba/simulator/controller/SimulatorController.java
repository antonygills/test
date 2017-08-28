/******************************************************
* Copyright (c) August 2017, Gills Antony.
* All Rights Reserved
* 
* This file can be redistributed and/or modified,
* under the terms of the GNU General Public License
* as published by the Free Software Foundation,
* either version 3 of the License, or any later version.
*******************************************************/
package com.cba.simulator.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cba.simulator.constant.AppConstants;
import com.cba.simulator.databaseservice.DataReadService;
import com.cba.simulator.dataobject.EnvironmentDetailsDO;
import com.cba.simulator.dataobject.SimulatorInputDTO;
import com.cba.simulator.dataobject.SimulatorOutputDTO;
import com.cba.simulator.exception.DAOException;
import com.cba.simulator.exception.UtilsException;

/**
 * Controls the flow of program. <br>
 * <p>
 * This class is responsible for the following,
 * <ul>
 * <li>historical data is fetched from database based on the input
 * arguments.</li>
 * <li>simulate the future environmental condition based on the historical
 * data.</li>
 * </ul>
 * 
 * @author Gills Antony
 */
public class SimulatorController {

	private static final Logger LOGGER = Logger.getLogger(SimulatorController.class);

	DataReadService dataReadService = new DataReadService();
	SimulatorExecutor simulationExecution = new SimulatorExecutor();
	List<SimulatorOutputDTO> outputDOList = new ArrayList<SimulatorOutputDTO>();

	/**
	 * Takes decision based on the input arguments to fetch historical data.
	 * <p>
	 * Simulate Environment conditions based on the historical data.
	 * 
	 * @param simulatorInputDTO
	 *            - inputs data object.
	 * @throws DAOException
	 *             if anything fails related to database.
	 * @throws UtilsException
	 *             if any util function fails.
	 */
	public void startExecution(final SimulatorInputDTO simulatorInputDTO) throws DAOException, UtilsException {
		LOGGER.debug("Enter startExecution.");
		List<EnvironmentDetailsDO> environmentDataList = null;

		if (AppConstants.PREDICT_ONE_DAY.equals(simulatorInputDTO.getInputOperation())) {
			environmentDataList = dataReadService.fetchEnvironmentDataForOneDay(simulatorInputDTO.getStartDayOfYear());
		}
		if (AppConstants.PREDICT_DEFAULT_ONE_WEEK.equals(simulatorInputDTO.getInputOperation())
				|| AppConstants.PREDICT_FOR_GIVEN_PERIOD.equals(simulatorInputDTO.getInputOperation())) {
			environmentDataList = dataReadService.fetchEnvironmentDataForPeriod(simulatorInputDTO.getStartDayOfYear(),
					simulatorInputDTO.getEndDayOfYear());
		}
		simulationExecution.simulateEnvironmentConditions(environmentDataList, simulatorInputDTO);
		LOGGER.debug("Exit startExecution.");
	}

}
