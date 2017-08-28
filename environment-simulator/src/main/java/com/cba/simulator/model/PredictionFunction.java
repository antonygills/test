/******************************************************
* Copyright (c) August 2017, Gills Antony.
* All Rights Reserved
* 
* This file can be redistributed and/or modified,
* under the terms of the GNU General Public License
* as published by the Free Software Foundation,
* either version 3 of the License, or any later version.
*******************************************************/
package com.cba.simulator.model;

import java.util.List;

import org.apache.log4j.Logger;

import com.cba.simulator.constant.AppConstants;
import com.cba.simulator.dataobject.SimulatorOutputDTO;
import com.cba.simulator.dataobject.WeatherDetailsDO;
import com.cba.simulator.util.Utils;
import com.workday.insights.timeseries.arima.Arima;
import com.workday.insights.timeseries.arima.struct.ArimaParams;
import com.workday.insights.timeseries.arima.struct.ForecastResult;

/**
 * This class is responsible for the following,
 * <ul>
 * <li>Predicts the future weather parameter using ARIMA model.</li>
 * <li>Identify weather type using the predicted parameters.</li>
 * </ul>
 * 
 * @author Gills Antony
 */
public class PredictionFunction {
	public static final Logger LOGGER = Logger.getLogger(PredictionFunction.class);

	/**
	 * This method is used to predict the weather details for a single day (-d
	 * option).
	 * 
	 * @param weatherDetails
	 *            - list of weather details of one particular day of year of a
	 *            location.
	 * @param outputDO
	 *            - predicted output conditions.
	 * @return predicted output environment details of particular location for one
	 *         day.
	 */
	public SimulatorOutputDTO predictWeather(final List<WeatherDetailsDO> weatherDetails,
			final SimulatorOutputDTO outputDO) {
		return getLocationWeatherInfo(weatherDetails, outputDO);
	}

	/**
	 * This Method performs the mathematical calculations to obtain the weather
	 * informations.
	 * 
	 * @param weatherDetails
	 *            - list of weather details of one particular day of year of a
	 *            location.
	 * @param simulatedOutputDO
	 *            - predicted output conditions.
	 * @return predicted output environment details of particular location for one.
	 */
	public SimulatorOutputDTO getLocationWeatherInfo(final List<WeatherDetailsDO> weatherDetails,
			SimulatorOutputDTO simulatedOutputDO) {
		int sizeOfList = weatherDetails.size();

		double[] temperatureList = new double[sizeOfList];
		double[] pressureList = new double[sizeOfList];
		double[] humidityList = new double[sizeOfList];

		for (int i = 0; i < sizeOfList; i++) {

			temperatureList[i] = weatherDetails.get(i).getTemperature();
			pressureList[i] = weatherDetails.get(i).getPressure();
			humidityList[i] = weatherDetails.get(i).getHumidity();
		}

		double resultantTemperature = predictWeatherParams(temperatureList);
		double resultantPressure = predictWeatherParams(pressureList);
		int resultantHumidity = (int) predictWeatherParams(humidityList);

		String weatherCondition = getWeatherCondition(resultantTemperature, resultantPressure);

		simulatedOutputDO.setTemperature(Utils.getRoundedValue(resultantTemperature));
		simulatedOutputDO.setPressure(Utils.getRoundedValue(resultantPressure));
		simulatedOutputDO.setHumidity(resultantHumidity);
		simulatedOutputDO.setWeatherCondition(weatherCondition);
		return simulatedOutputDO;
	}

	/**
	 * This method generates forecast values based on input values. The prediction
	 * model is based on ARIMA. Reference:
	 * https://github.com/Workday/timeseries-forecast. ARIMA model parameters are
	 * passed to obtain forecast result.
	 * 
	 * @param inputData
	 *            - input data
	 * @return double[] forecastData, forecasted output
	 */
	public double predictWeatherParams(final double[] inputData) {
		int p = 1;
		int d = 0;
		int q = 1;
		int P = 1;
		int D = 1;
		int Q = 0;
		int m = 0;
		int forecastSize = 1;
		ArimaParams arimaParams = new ArimaParams(p, d, q, P, D, Q, m);
		ForecastResult forecastResult = Arima.forecast_arima(inputData, forecastSize, arimaParams);
		double[] forecastData = forecastResult.getForecast();
		return forecastData[0];
	}

	/**
	 * predict the weather type based on the weather parameters.
	 *
	 * @param temperature
	 *            - temperature
	 * @param pressure
	 *            - pressure
	 * @return weather type - predicted weather type
	 */
	public String getWeatherCondition(final double temperature, final double pressure) {
		String weatherType = null;
		if (temperature < 18 && pressure > 75) {
			weatherType = AppConstants.WEATHER_FOG;
		} else if (temperature < 18 && pressure >= 65 && pressure <= 75) {
			weatherType = AppConstants.WEATHER_RAIN;
		} else if (temperature > 18 && temperature <= 25) {
			weatherType = AppConstants.WEATHER_WARM;
		} else if (temperature >= 10 && temperature <= 18) {
			weatherType = AppConstants.WEATHER_COOL;
		} else if (temperature < 10) {
			weatherType = AppConstants.WEATHER_COLD;
		} else if (temperature > 25) {
			weatherType = AppConstants.WEATHER_HOT;
		}
		return weatherType;
	}
}
