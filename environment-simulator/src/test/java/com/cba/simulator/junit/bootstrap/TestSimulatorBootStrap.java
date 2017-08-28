package com.cba.simulator.junit.bootstrap;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.cba.simulator.bootstrap.SimulatorBootstrap;
import com.cba.simulator.dataobject.SimulatorInputDTO;
import com.cba.simulator.exception.BusinessException;
import com.cba.simulator.exception.UtilsException;

@RunWith(Parameterized.class)
public class TestSimulatorBootStrap {

	private String[] inputInstance;
	private SimulatorInputDTO expectedResult;
	private boolean expectedValidationResult;

	/**
	 * constructor that takes in one row of test data from collection Parameters.
	 */
	public TestSimulatorBootStrap(String[] inputInstance, SimulatorInputDTO expectedResult,
			boolean expectedValidationResult) {
		this.inputInstance = inputInstance;
		this.expectedResult = expectedResult;
		this.expectedValidationResult = expectedValidationResult;
	}

	/**
	 * generates and returns test data
	 * 
	 * @return Collection where each entry in the Collection will be the input data
	 *         for one iteration of the test.
	 */
	@Parameterized.Parameters
	public static Collection inputParametersList() {
		String[] inputInstance1 = {};
		SimulatorInputDTO expectedResult1 = new SimulatorInputDTO();
		expectedResult1.setStartDayOfYear(0);
		boolean expectedValidationResult1 = true;

		String[] inputInstance2 = { "2020-01-15" };
		SimulatorInputDTO expectedResult2 = new SimulatorInputDTO();
		expectedResult2.setStartDayOfYear(15);
		boolean expectedValidationResult2 = true;

		String[] inputInstance3 = { "2020-01-22", "2020-01-26" };
		SimulatorInputDTO expectedResult3 = new SimulatorInputDTO();
		expectedResult3.setStartDayOfYear(22);
		boolean expectedValidationResult3 = true;

		return Arrays.asList(new Object[][] { { inputInstance1, expectedResult1, expectedValidationResult1 },
				{ inputInstance2, expectedResult2, expectedValidationResult2 },
				{ inputInstance3, expectedResult3, expectedValidationResult3 } });
	}

	@Test
	public void testValidateInputArgs() throws BusinessException, UtilsException {
		assertEquals(expectedValidationResult, SimulatorBootstrap.validateInputArgs(inputInstance));
	}

	@Test
	public void testProcessInputArgs() throws UtilsException {
		SimulatorInputDTO simulatorInputDTO = SimulatorBootstrap.processInputArgs(inputInstance);
		assertEquals(expectedResult.getStartDayOfYear(), simulatorInputDTO.getStartDayOfYear());
	}
}
