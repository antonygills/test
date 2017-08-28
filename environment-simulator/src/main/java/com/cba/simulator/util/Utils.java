package com.cba.simulator.util;

/**
 * Util class containing common functions.
 * 
 * @author Gills Antony
 */
public class Utils {

	/**
	 * Round input double to one decimal places.
	 * 
	 * @param inputValue
	 *            - input value which needs to be rounded.
	 * @return rounded value.
	 */
	public static double getRoundedValue(final double inputValue) {
		return (double) Math.round(inputValue * 10) / 10;
	}

	private Utils() {
	}
}
