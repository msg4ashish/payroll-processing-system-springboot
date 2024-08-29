package com.pps.payroll.service;

import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.util.List;

/**
 * Interface for processing CSV files in a payroll system. Defines contract for
 * services that process CSV files,
 */
public interface CSVFileProcessorService {

	/**
	 * Processes the CSV file located at the given file path.
	 * @param filePath The path of the CSV file to be processed.
	 */
	public void processFile(String filePath) throws IOException, CsvException;

	/**
	 * Retrieves list of invalid records found during CSV file processing.
	 * 
	 * @return List of invalid records.
	 */
	public List<String[]> getInvalidRecords();

	/**
	 * Retrieves total number of records processed from CSV file.
	 * 
	 * @return Total number of records.
	 */
	public Integer getTotalRecordsCount();

}
