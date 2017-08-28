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
 * Data transfer object for storing and transfering of input data.
 * 
 * @author Gills Antony
 */
public class SimulatorInputDTO {
	private Date startDate;
	private Date endDate;
	private int startDayOfYear;
	private int endDayOfYear;
	private String inputOperation;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getStartDayOfYear() {
		return startDayOfYear;
	}

	public void setStartDayOfYear(int startDayOfYear) {
		this.startDayOfYear = startDayOfYear;
	}

	public int getEndDayOfYear() {
		return endDayOfYear;
	}

	public void setEndDayOfYear(int endDayOfYear) {
		this.endDayOfYear = endDayOfYear;
	}

	public String getInputOperation() {
		return inputOperation;
	}

	public void setInputOperation(String inputOperation) {
		this.inputOperation = inputOperation;
	}

}
