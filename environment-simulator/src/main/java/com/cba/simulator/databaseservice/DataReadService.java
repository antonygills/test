package com.cba.simulator.databaseservice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.cba.simulator.connector.MyBatisConnectionFactory;
import com.cba.simulator.constant.ErrorConstants;
import com.cba.simulator.dao.DataReadDAO;
import com.cba.simulator.dataobject.EnvironmentDetailsDO;
import com.cba.simulator.exception.DAOException;

/**
 * Database service class to perform database operation.
 * 
 * @author Gills Antony
 */
public class DataReadService {
	public static final Logger LOGGER = Logger.getLogger(DataReadService.class);

	/**
	 * Fetch entire environment historical data from database.
	 * 
	 * @return list of historical Environment Details
	 * @throws DAOException
	 *             if the method fails to fetch data from database.
	 */
	public List<EnvironmentDetailsDO> fetchEnvironmentDataFull() throws DAOException {
		LOGGER.debug("Enter fetchEnvironmentDataFull.");
		List<EnvironmentDetailsDO> environmentData = null;
		SqlSession session = null;
		try {
			session = MyBatisConnectionFactory.getSession().openSession();
			DataReadDAO mybatisReadDAO = session.getMapper(DataReadDAO.class);
			environmentData = mybatisReadDAO.fetchEnvironmentDataFull();
		} catch (Exception e) {
			throw new DAOException(ErrorConstants.DAO_MYBATIS_FETCH_FAILED, e);
		} finally {
			if (session != null)
				session.close();
		}
		LOGGER.debug("Exit fetchEnvironmentDataFull.");
		return environmentData;
	}

	/**
	 * Fetch environment historical data for a particular day of year from database.
	 * 
	 * @param dayOfYear
	 *            - Day of Year.
	 * @return list of historical Environment Details
	 * @throws DAOException
	 *             if the method fails to fetch data from database.
	 */
	public List<EnvironmentDetailsDO> fetchEnvironmentDataForOneDay(final int dayOfYear) throws DAOException {
		LOGGER.debug("Enter fetchEnvironmentDataForOneDay with input dayOfYear - " + dayOfYear);
		List<EnvironmentDetailsDO> environmentData = null;
		SqlSession session = null;
		try {
			session = MyBatisConnectionFactory.getSession().openSession();
			DataReadDAO mybatisReadDAO = session.getMapper(DataReadDAO.class);
			environmentData = mybatisReadDAO.fetchEnvironmentDataForOneDay(dayOfYear);
		} catch (Exception e) {
			throw new DAOException(ErrorConstants.DAO_MYBATIS_FETCH_FAILED, e);
		} finally {
			if (session != null)
				session.close();
		}
		LOGGER.debug("Exit fetchEnvironmentDataForOneDay.");
		return environmentData;
	}

	/**
	 * Fetch environment historical data between two day of year values from
	 * database.
	 * 
	 * @param startDayOfYear
	 *            - start Day of Year.
	 * @param endDayOfYear
	 *            - end of Year.
	 * @return list of historical Environment Details
	 * @throws DAOException
	 *             if the method fails to fetch data from database.
	 */
	public List<EnvironmentDetailsDO> fetchEnvironmentDataForPeriod(final int startDayOfYear, final int endDayOfYear)
			throws DAOException {
		LOGGER.debug("Enter fetchEnvironmentDataForPeriod with startDayOfYear - " + startDayOfYear
				+ " and endDayOfYear - " + endDayOfYear);
		List<EnvironmentDetailsDO> environmentData = null;
		SqlSession session = null;
		try {
			session = MyBatisConnectionFactory.getSession().openSession();
			DataReadDAO mybatisReadDAO = session.getMapper(DataReadDAO.class);
			environmentData = mybatisReadDAO.fetchEnvironmentDataForPeriod(startDayOfYear, endDayOfYear);
		} catch (Exception e) {
			throw new DAOException(ErrorConstants.DAO_MYBATIS_FETCH_FAILED, e);
		} finally {
			if (session != null)
				session.close();
		}
		LOGGER.debug("Exit fetchEnvironmentDataForPeriod.");
		return environmentData;

	}

	/**
	 * Fetch the location details of the input location Name database.
	 * 
	 * @param locationName
	 *            - Location Name.
	 * @return environment details of the input location.
	 * @throws DAOException
	 *             if the method fails to fetch data from database.
	 */
	public EnvironmentDetailsDO getLocationDetails(final String locationName) throws DAOException {
		LOGGER.debug("Enter getLocationDetails with locationName - " + locationName);

		EnvironmentDetailsDO environmentData = null;
		SqlSession session = null;
		try {
			session = MyBatisConnectionFactory.getSession().openSession();
			DataReadDAO mybatisReadDAO = session.getMapper(DataReadDAO.class);
			environmentData = mybatisReadDAO.getLocationDetails(locationName);
		} catch (Exception e) {
			throw new DAOException(ErrorConstants.DAO_MYBATIS_FETCH_FAILED, e);
		} finally {
			if (session != null)
				session.close();
		}
		LOGGER.debug("Exit getLocationDetails.");
		return environmentData;
	}

}
