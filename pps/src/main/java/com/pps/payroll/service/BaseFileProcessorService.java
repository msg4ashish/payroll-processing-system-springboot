package com.pps.payroll.service;

import java.io.FileNotFoundException;
import java.io.FileReader;

public interface BaseFileProcessorService {

	public FileReader readFile(String filePath) throws FileNotFoundException;

}
