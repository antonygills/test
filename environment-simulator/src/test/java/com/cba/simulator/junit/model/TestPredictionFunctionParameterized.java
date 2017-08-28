package com.cba.simulator.junit.model;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.cba.simulator.constant.AppConstants;
import com.cba.simulator.exception.BusinessException;
import com.cba.simulator.exception.UtilsException;
import com.cba.simulator.model.PredictionFunction;

@RunWith(Parameterized.class)
public class TestPredictionFunctionParameterized {
	
	static PredictionFunction predictionFunction;
	private double inputTemperature;
	private double inputPressure;
	private String expectedResult;

	/**
	 * Sets up the test inputs. (Called once in this class.)
	 * 
	 * @throws ParseException
	 * 
	 */
	@BeforeClass
	public static void setUpOnce() throws ParseException {
		predictionFunction = new PredictionFunction();
	}

	/**
	 * constructor that takes in one row of test data from collection Parameters.
	 */
	public TestPredictionFunctionParameterized(double inputTemperature, double inputPressure, String expectedResult) {
		this.inputTemperature = inputTemperature;
		this.inputPressure = inputPressure;
		this.expectedResult = expectedResult;
	}

	/**
	 * generates and returns test data
	 * 
	 * @return Collection where each entry in the Collection will be the input data
	 *         for one iteration of the test.
	 */
	@Parameterized.Parameters
	public static Collection inputParametersList() {
		double inputTemperature1 = 15;
		double inputPressure1 = 80;
		String expectedResult1 = AppConstants.WEATHER_FOG;

		double inputTemperature2 = 15;
		double inputPressure2 = 70;
		String expectedResult2 = AppConstants.WEATHER_RAIN;

		double inputTemperature3 = 20;
		double inputPressure3 = 50;
		String expectedResult3 = AppConstants.WEATHER_WARM;

		return Arrays.asList(new Object[][] { { inputTemperature1, inputPressure1, expectedResult1 },
				{ inputTemperature2, inputPressure2, expectedResult2 },
				{ inputTemperature3, inputPressure3, expectedResult3 } });
	}

	@Test
	public void testGetWeatherCondition() throws BusinessException, UtilsException {
		assertEquals(expectedResult, predictionFunction.getWeatherCondition(inputTemperature, inputPressure));
	}

}
