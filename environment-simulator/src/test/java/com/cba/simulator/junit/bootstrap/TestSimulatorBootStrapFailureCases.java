package com.cba.simulator.junit.bootstrap;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.cba.simulator.bootstrap.SimulatorBootstrap;
import com.cba.simulator.constant.ErrorConstants;
import com.cba.simulator.exception.BusinessException;
import com.cba.simulator.exception.UtilsException;

/**
 * @author DELL
 *
 */
@RunWith(Parameterized.class)
public class TestSimulatorBootStrapFailureCases {
	
	private String[] inputInstance;
	private String expectedResult;

	/**
	 * constructor that takes in one row of test data from collection Parameters.
	 */
	public TestSimulatorBootStrapFailureCases(String[] inputInstance, String expectedResult) {
		this.inputInstance = inputInstance;
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
		String[] inputInstance1 = { "2017-01-22" };
		String[] inputInstance2 = { "2020-01-10", "2020-01-08" };
		String[] inputInstance3 = { "2020-01-10", "2020-01-11", "2020-01-12" };

		return Arrays.asList(new Object[][] { { inputInstance1, ErrorConstants.BUSINESS_INVALIDARGS_PASTDATE },
				{ inputInstance2, ErrorConstants.BUSINESS_INVALIDARGS_ENDDATE },
				{ inputInstance3, ErrorConstants.BUSINESS_INVALIDARGS } });
	}

	@Test
	public void testValidateInputArgs() throws UtilsException {
		try {
			SimulatorBootstrap.validateInputArgs(inputInstance);
		} catch (BusinessException e) {
			assertEquals(expectedResult, e.getMessage());
		}

	}
}
