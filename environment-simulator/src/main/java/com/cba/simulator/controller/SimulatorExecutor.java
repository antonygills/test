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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.cba.simulator.constant.AppConstants;
import com.cba.simulator.databaseservice.DataReadService;
import com.cba.simulator.dataobject.EnvironmentDetailsDO;
import com.cba.simulator.dataobject.SimulatorInputDTO;
import com.cba.simulator.dataobject.SimulatorOutputDTO;
import com.cba.simulator.dataobject.WeatherDetailsDO;
import com.cba.simulator.exception.UtilsException;
import com.cba.simulator.model.PredictionFunction;
import com.cba.simulator.util.DateUtils;
import com.cba.simulator.util.FileUtils;

/**
 * This class is responsible for the following,
 * <ul>
 * <li>Prepare historical environment data.</li>
 * <li>Predicts future weather conditions per location from historical
 * data.</li>
 * <li>Prepare output content.</li>
 * <li>Writes prepared content to output file..</li>
 * </ul>
 * 
 * @author Gills Antony
 */
public class SimulatorExecutor {
	private static final Logger LOGGER = Logger.getLogger(SimulatorExecutor.class);

	DataReadService dataReadService = new DataReadService();
	List<SimulatorOutputDTO> outputDOList = new ArrayList<SimulatorOutputDTO>();
	PredictionFunction predictionFunction = new PredictionFunction();

	/**
	 * Method to simulate future environment conditions from historical data.
	 * <p>
	 * This method does the following, Prepare historical data per location Predicts
	 * output from historical data Write output to text file.
	 * 
	 * @param environmentDataList
	 *            - contains list of historical environment details of different
	 *            locations.
	 * @param simulatorInputDTO
	 *            - arguments passed as input.
	 * @throws UtilsException if any Util operations fails.
	 */
	public void simulateEnvironmentConditions(List<EnvironmentDetailsDO> environmentDataList,
			SimulatorInputDTO simulatorInputDTO) throws UtilsException {
		LOGGER.debug("Enter simulateEnvironmentConditions.");
		List<SimulatorOutputDTO> environmentSimulationResult = null;
		if (environmentDataList != null && !environmentDataList.isEmpty()) {
			for (EnvironmentDetailsDO environmentData : environmentDataList) {
				// per location data
				environmentSimulationResult = simulateConditionsPerLocation(environmentData, simulatorInputDTO);
			}

			// TODO remove this after testing
			System.out.println("****************************************");
			System.out.println(environmentSimulationResult);
			System.out.println("****************************************");

			if (environmentSimulationResult != null)
				prepareOutputContent(environmentSimulationResult);

		}
		LOGGER.debug("Exit simulateEnvironmentConditions.");
	}

	/**
	 * Method to simulate future environment conditions per location from historical
	 * data.
	 * <p>
	 * This method does the following, Prepare historical data for each location
	 * Predicts output from historical data.
	 * 
	 * @param environmentData
	 *            - contains historical environment details of one location.
	 * @param simulatorInputDTO
	 *            - arguments passed as input.
	 * @return list of simulator output
	 */
	public List<SimulatorOutputDTO> simulateConditionsPerLocation(EnvironmentDetailsDO environmentData,
			SimulatorInputDTO simulatorInputDTO) {
		LOGGER.debug("Enter simulateConditionsForLocation with location - " + environmentData.getLocationName());
		Map<Integer, List<WeatherDetailsDO>> weatherDetailsMap = prepareEnvironmentData(
				environmentData.getWeatherDetails());

		for (Entry<Integer, List<WeatherDetailsDO>> pair : weatherDetailsMap.entrySet()) {
			SimulatorOutputDTO simulatorOutputDTO;
			int dayOfYearForPrediction = pair.getKey();
			simulatorOutputDTO = prepareOutputDO(environmentData, dayOfYearForPrediction, simulatorInputDTO);
			// OutputDO out = regressionModel.predictWeather(pair.getValue(), outputDO);
			simulatorOutputDTO = predictionFunction.predictWeather(pair.getValue(), simulatorOutputDTO);
			outputDOList.add(simulatorOutputDTO);
		}
		LOGGER.debug("Exit prepareEnvironmentData.");
		return outputDOList;
	}

	/**
	 * Creates a map with key as day of year and value as list of weather details.
	 * 
	 * @param weatherDetailsList
	 *            - contains list of weather details of one location.
	 * @return map with day of year(key) and list of weather details(value).
	 */
	public Map<Integer, List<WeatherDetailsDO>> prepareEnvironmentData(List<WeatherDetailsDO> weatherDetailsList) {
		Map<Integer, List<WeatherDetailsDO>> weatherDetailsMap = new HashMap<Integer, List<WeatherDetailsDO>>();
		for (WeatherDetailsDO weatherDetails : weatherDetailsList) {
			if (!weatherDetailsMap.containsKey(weatherDetails.getDayofyear())) {
				weatherDetailsMap.put(weatherDetails.getDayofyear(), new ArrayList<WeatherDetailsDO>());
			}
			weatherDetailsMap.get(weatherDetails.getDayofyear()).add(weatherDetails);
		}
		return weatherDetailsMap;
	}

	/**
	 * Prepare output data and write to output file.
	 * 
	 * @param environmentSimulationResult
	 *            - output list of environmental conditions.
	 * @throws UtilsException
	 */
	private void prepareOutputContent(List<SimulatorOutputDTO> environmentSimulationResult) throws UtilsException {
		LOGGER.debug("Enter prepareOutputContent.");
		String outputPath = AppConstants.OUTPUT_FILE_PATH;
		String forecastedData = environmentSimulationResult.toString().substring(2,
				environmentSimulationResult.toString().length() - 3);
		FileUtils.writeOutputToFile(outputPath, forecastedData.replace("||,", ""));
		LOGGER.debug("Exit prepareOutputContent.");
	}

	/**
	 * Insert details to output object.
	 * 
	 * @param environmentData
	 *            - output environment details of a day for the location.
	 * @param dayOfYearForPrediction
	 *            - the day for which prediction is done.
	 * @param simulatorInputDTO
	 *            - arguments passed as input.
	 * @return predicted output environment details of particular location for one
	 *         day.
	 */
	public SimulatorOutputDTO prepareOutputDO(EnvironmentDetailsDO environmentData, int dayOfYearForPrediction,
			SimulatorInputDTO simulatorInputDTO) {
		SimulatorOutputDTO simulatorOutputDTO = new SimulatorOutputDTO();
		int yearForPrediction = DateUtils.getYearFromDate(simulatorInputDTO.getStartDate());
		if (dayOfYearForPrediction < DateUtils.getDayOfYear(simulatorInputDTO.getStartDate())) {
			yearForPrediction = DateUtils.getYearFromDate(simulatorInputDTO.getEndDate());
		}
		Date predictionDate = DateUtils.getDateFromDayOfYear(dayOfYearForPrediction, yearForPrediction);
		String predictionDateTime = null;
		predictionDateTime = DateUtils.getFormattedeDateTime(predictionDate);

		simulatorOutputDTO.setDateTime(predictionDateTime);
		simulatorOutputDTO.setLocationName(environmentData.getLocationName());
		simulatorOutputDTO.setLatitude(environmentData.getLatitude());
		simulatorOutputDTO.setLongitude(environmentData.getLongitude());
		simulatorOutputDTO.setElevation(environmentData.getElevation());
		return simulatorOutputDTO;
	}
}
