/******************************************************
* Copyright (c) August 2017, Gills Antony.
* All Rights Reserved
* 
* This file can be redistributed and/or modified,
* under the terms of the GNU General Public License
* as published by the Free Software Foundation,
* either version 3 of the License, or any later version.
*******************************************************/
package com.cba.simulator.junit.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cba.simulator.dataobject.SimulatorOutputDTO;
import com.cba.simulator.dataobject.WeatherDetailsDO;
import com.cba.simulator.model.PredictionFunction;

public class TestPredictionFunction {
	private static List<WeatherDetailsDO> inputWeatherDetails;
	private static SimulatorOutputDTO simulatorOutputDTO;
	private static PredictionFunction predictionFunction;

	@BeforeClass
	public static void setUpOnce() throws ParseException {
		predictionFunction = new PredictionFunction();
		inputWeatherDetails = new ArrayList<WeatherDetailsDO>();
		generateSampleHistoricalData();
		simulatorOutputDTO = new SimulatorOutputDTO();
	}

	@Test
	public void testGetLocationWeatherInfo() {
		SimulatorOutputDTO output = predictionFunction.getLocationWeatherInfo(inputWeatherDetails, simulatorOutputDTO);
		assertThat("Temperature Value Not predicted", output.getTemperature(), greaterThan(0.0));
		assertThat("Pressure Value Not predicted", output.getPressure(), greaterThan(0.0));
		assertThat("Humidity Value Not predicted", output.getHumidity(), greaterThan(0));
	}

	/**
	 * to generate dummy historical data list.
	 */
	private static void generateSampleHistoricalData() {
		Random randomGenerator = new Random();
		WeatherDetailsDO weatherDetailsDO;
		for (int i = 0; i <= 8; i++) {
			weatherDetailsDO = new WeatherDetailsDO();
			weatherDetailsDO.setTemperature(randomGenerator.nextInt(20));
			weatherDetailsDO.setPressure(randomGenerator.nextInt(80));
			weatherDetailsDO.setHumidity(randomGenerator.nextInt(1010));
			inputWeatherDetails.add(weatherDetailsDO);
		}
	}

}
