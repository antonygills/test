/******************************************************
* Copyright (c) August 2017, Gills Antony.
* All Rights Reserved
* 
* This file can be redistributed and/or modified,
* under the terms of the GNU General Public License
* as published by the Free Software Foundation,
* either version 3 of the License, or any later version.
*******************************************************/
package com.cba.simulator.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.cba.simulator.constant.ErrorConstants;
import com.cba.simulator.exception.UtilsException;

/**
 * Util class containing common file operations.
 * 
 * @author Gills Antony
 */
public class FileUtils {

	private static final Logger LOGGER = Logger.getLogger(FileUtils.class);

	/**
	 * Creates file if the input filePath does not exist, then write the input
	 * content to the file.
	 * 
	 * @param filePath
	 *            - path of the to which data will be written.
	 * @param content
	 *            - content that needs to be written to the file.
	 * @throws UtilsException
	 *             if file write operation fails.
	 */
	public static void writeOutputToFile(String filePath, String content) throws UtilsException {
		LOGGER.debug("Enter writeOutputToFile.");
		FileWriter fileWriter = null;
		File file = new File(filePath);

		try {
			if (!file.exists() && !file.isDirectory()) {
				file.getParentFile().mkdirs();
				boolean fileCreationStatus = file.createNewFile();
				LOGGER.debug("Output File creation : " + fileCreationStatus);
			}
			fileWriter = new FileWriter(filePath);
			fileWriter.write(content);
		} catch (Exception e) {
			throw new UtilsException(ErrorConstants.UTILS_FILE_WRITE_FAILED, e);
		} finally {
			try {
				if (fileWriter != null) {
					fileWriter.flush();
					fileWriter.close();
					LOGGER.info("Successfully wrote output to the file : " + filePath);
				}
			} catch (IOException e) {
				throw new UtilsException(ErrorConstants.UTILS_UNABLE_FIND_FILE_PATH, e);
			} catch (Exception e) {
				throw new UtilsException(ErrorConstants.UTILS_FILE_CLOSE_FAILED, e);
			}
		}
		LOGGER.debug("Exit writeOutputToFile.");
	}

	private FileUtils() {
	}

}
