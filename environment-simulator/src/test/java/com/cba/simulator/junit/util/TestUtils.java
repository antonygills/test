package com.cba.simulator.junit.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.cba.simulator.util.Utils;

public class TestUtils {

	@Test
	public void testGetRoundedValue() {
		double input = 20.8999;
		assertEquals(20.9, Utils.getRoundedValue(input), 0.1);
	}
}
