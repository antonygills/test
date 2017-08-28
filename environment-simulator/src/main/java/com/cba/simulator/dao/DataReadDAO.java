package com.cba.simulator.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cba.simulator.dataobject.EnvironmentDetailsDO;
import com.cba.simulator.exception.DAOException;

/**
 * Interface class for mybatis, whose implementation is in DataReadMapper.xml
 * file.
 * 
 * @author Gills Antony
 */
public interface DataReadDAO {

	/**
	 * Fetch entire environment historical data from database.
	 * 
	 * @return list of all Environmental Details.
	 * @throws DAOException
	 *             if the method fails to fetch data from database.
	 */
	public List<EnvironmentDetailsDO> fetchEnvironmentDataFull() throws DAOException;

	/**
	 * Fetch environment historical data for a particular day of year from database.
	 * 
	 * @param dayOfYear
	 *            - Day of Year.
	 * @return list of Environmental Details.
	 * @throws DAOException
	 *             if the method fails to fetch data from database.
	 */
	public List<EnvironmentDetailsDO> fetchEnvironmentDataForOneDay(final int dayOfYear) throws DAOException;

	/**
	 * Fetch environment historical data between two day of year values from
	 * database.
	 * 
	 * @param startDayOfYear
	 *            - start Day of Year.
	 * @param endDayOfYear
	 *            - end Day of Year.
	 * @return list of Environmental Details.
	 * @throws DAOException
	 *             if the method fails to fetch data from database.
	 */
	public List<EnvironmentDetailsDO> fetchEnvironmentDataForPeriod(final @Param("startDayOfYear") int startDayOfYear,
			final @Param("endDayOfYear") int endDayOfYear) throws DAOException;

	/**
	 * Fetch the location details of the input location Name database.
	 * 
	 * @param locationName
	 *            - location name.
	 * @return Environmental Details of input location.
	 * @throws DAOException
	 *             if the method fails to fetch data from database.
	 */
	public EnvironmentDetailsDO getLocationDetails(final String locationName) throws DAOException;
}
