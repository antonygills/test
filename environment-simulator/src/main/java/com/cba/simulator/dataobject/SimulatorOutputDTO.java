/******************************************************
* Copyright (c) August 2017, Gills Antony.
* All Rights Reserved
* 
* This file can be redistributed and/or modified,
* under the terms of the GNU General Public License
* as published by the Free Software Foundation,
* either version 3 of the License, or any later version.
*******************************************************/
package com.cba.simulator.dataobject;

import com.cba.simulator.constant.AppConstants;

/**
 * Data transfer object for storing and transfering of output data (predicted
 * environment details).
 * 
 * @author Gills Antony
 */
public class SimulatorOutputDTO {
	private String locationName;
	private String latitude;
	private String longitude;
	private int elevation;
	private String dateTime;
	private double temperature;
	private double pressure;
	private int humidity;
	private String weatherCondition;

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public int getElevation() {
		return elevation;
	}

	public void setElevation(int elevation) {
		this.elevation = elevation;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public String getWeatherCondition() {
		return weatherCondition;
	}

	public void setWeatherCondition(String weatherCondition) {
		this.weatherCondition = weatherCondition;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("\n").append(locationName).append("|").append(latitude).append(",").append(longitude).append(",")
				.append(elevation).append("|").append(dateTime).append("|").append(weatherCondition).append("|")
				.append(temperature).append("|").append(pressure).append("|").append(humidity)
				.append(AppConstants.SPLITLINE_PATTERN);
		return builder.toString();
	}

}
