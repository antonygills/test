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
