package com.pps.payroll.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;

/**
 * Class for all common utlity functions
 */
public class CommonUtils {
	
	public static boolean checkFileExists(String filePath) throws IOException {
		if (StringUtils.isEmpty(filePath)) {
			return false;
		}
		final Path path = Paths.get(filePath);
		return Files.exists(path);
	}

}
