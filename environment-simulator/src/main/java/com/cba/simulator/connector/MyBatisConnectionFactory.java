/******************************************************
* Copyright (c) August 2017, Gills Antony.
* All Rights Reserved
* 
* This file can be redistributed and/or modified,
* under the terms of the GNU General Public License
* as published by the Free Software Foundation,
* either version 3 of the License, or any later version.
*******************************************************/
package com.cba.simulator.connector;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

/**
 * Creates myBatis sql session based on the configurations provided in myBatis
 * configuration xml file.
 * 
 * @author Gills Antony
 */
public class MyBatisConnectionFactory {

	public static final Logger LOGGER = Logger.getLogger(MyBatisConnectionFactory.class);

	private static SqlSessionFactory sqlSession;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("mybatisConfig.xml");
			if (sqlSession == null) {
				sqlSession = new SqlSessionFactoryBuilder().build(reader);
			}
		} catch (IOException e) {
			LOGGER.error("Could not find MyBatis configuration xml :: " + e);
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSession;
	}

	private MyBatisConnectionFactory() {
	}

}
