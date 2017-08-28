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

import java.util.List;

/**
 * Data Object corresponding to location table of Database.
 * <p>
 * Used for storing environment details(Location details and list of
 * corresponding weather condition for different dates for that location).
 * 
 * @author Gills Antony
 */
public class EnvironmentDetailsDO {
	private int locationId;
	private String locationName;
	private String locationCode;
	private String latitude;
	private String longitude;
	private int elevation;
	private List<WeatherDetailsDO> weatherDetails;

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
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

	public List<WeatherDetailsDO> getWeatherDetails() {
		return weatherDetails;
	}

	public void setWeatherDetails(List<WeatherDetailsDO> weatherDetails) {
		this.weatherDetails = weatherDetails;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EnvironmentDetailsDO [locationId=").append(locationId).append(", locationName=")
				.append(locationName).append(", locationCode=").append(locationCode).append(", latitude=")
				.append(latitude).append(", longitude=").append(longitude).append(", elevation=").append(elevation)
				.append(", weatherDetails=").append(weatherDetails).append("]");
		return builder.toString();
	}

}
