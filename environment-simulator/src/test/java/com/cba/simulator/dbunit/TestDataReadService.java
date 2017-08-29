package com.cba.simulator.dbunit;

import java.sql.Connection;

import java.sql.DriverManager;
import java.util.List;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Ignore;
import org.junit.Test;

import com.cba.simulator.databaseservice.DataReadService;
import com.cba.simulator.dataobject.EnvironmentDetailsDO;
import com.cba.simulator.exception.DAOException;

@Ignore
public class TestDataReadService extends DatabaseTestCase {

	private Connection jdbcConnection;
	private DataReadService dataReadService;

	/** Provide a connection to the database. */
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weather", "root", "password");
		return new DatabaseConnection(jdbcConnection);
	}

	/** Load the data */
	@Override
	protected IDataSet getDataSet() throws Exception {
		DataFileLoader loader = new FlatXmlDataFileLoader();
		return loader.load("/data-seed-for-dbunit.xml");
	}

	@Override
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.NONE; // by default (will do DELETE_ALL + INSERT)
	}

	@Override
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}

	/**
	 * Test case for testing if tables exist.
	 * 
	 * @throws Exception
	 *             any exception.
	 */
	@Test
	public void testTables() throws Exception {
		dataReadService = new DataReadService();
		IDataSet databaseDataSet = getConnection().createDataSet();
		ITable actualLocationTable = databaseDataSet.getTable("location");
		assertNotNull("location table not found", actualLocationTable);
		ITable actualWeatherDetailsTable = databaseDataSet.getTable("weather_details");
		assertNotNull("weather_details table not found", actualWeatherDetailsTable);
	}

	/**
	 * Test case for basic fetch operation.
	 * 
	 * @throws DAOException
	 *             if the method fails to fetch data from database.
	 */
	@Test
	public void testFetchEnvironmentDataForOneDay() throws DAOException {
		dataReadService = new DataReadService();
		EnvironmentDetailsDO environmentData = null;
		List<EnvironmentDetailsDO> environmentDataList = dataReadService.fetchEnvironmentDataForOneDay(1);
		assertNotNull("EnvironmentDataList shouldn't be null", environmentDataList);
		for (EnvironmentDetailsDO environmentDataInstance : environmentDataList) {
			if (1 == environmentDataInstance.getLocationId()) {
				environmentData = environmentDataInstance;
			}
		}
		assertEquals(1, environmentData.getLocationId());
		assertEquals("Sydney", environmentData.getLocationName());
		assertEquals("SYD", environmentData.getLocationCode());
		assertEquals("-33.52", environmentData.getLatitude());
		assertEquals("151.13", environmentData.getLongitude());
		assertEquals(58, environmentData.getElevation());
	}

}
