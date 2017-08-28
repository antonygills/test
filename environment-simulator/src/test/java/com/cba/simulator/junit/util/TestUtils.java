/******************************************************
* Copyright (c) August 2017, Gills Antony.
* All Rights Reserved
* 
* This file can be redistributed and/or modified,
* under the terms of the GNU General Public License
* as published by the Free Software Foundation,
* either version 3 of the License, or any later version.
*******************************************************/
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
