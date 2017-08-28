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

import java.util.Date;

/**
 * Data Object corresponding to weather_details table of Database.
 * <p>
 * Used for storing weather details of a particular location.
 * 
 * @author Gills Antony
 */
public class WeatherDetailsDO {
	private int id;
	private int locationId;
	private Date date;
	private String year;
	private int dayofyear;
	private double temperature;
	private int humidity;
	private double pressure;
	private String conditions;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getDayofyear() {
		return dayofyear;
	}

	public void setDayofyear(int dayofyear) {
		this.dayofyear = dayofyear;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WeatherDetailsDO [id=").append(id).append(", locationId=").append(locationId).append(", date=")
				.append(date).append(", year=").append(year).append(", dayofyear=").append(dayofyear)
				.append(", temperature=").append(temperature).append(", pressure=").append(pressure)
				.append(", humidity=").append(humidity).append(", conditions=").append(conditions).append("]");
		return builder.toString();
	}
}
