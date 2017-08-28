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
import org.junit.Test;

import com.cba.simulator.databaseservice.DataReadService;
import com.cba.simulator.dataobject.EnvironmentDetailsDO;
import com.cba.simulator.exception.DAOException;

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
	 */
	@Test
	public void testFetchEnvironmentDataForOneDay() throws DAOException {
		dataReadService = new DataReadService();
		List<EnvironmentDetailsDO> environmentData = dataReadService.fetchEnvironmentDataForOneDay(1);
		assertNotNull("EnvironmentData shouldn't be null", environmentData);
		assertEquals(1, environmentData.get(0).getLocationId());
		assertEquals("Sydney", environmentData.get(0).getLocationName());
		assertEquals("SYD", environmentData.get(0).getLocationCode());
		assertEquals("-33.52", environmentData.get(0).getLatitude());
		assertEquals("151.13", environmentData.get(0).getLongitude());
		assertEquals(58, environmentData.get(0).getElevation());
	}

}
