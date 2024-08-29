package com.pps.payroll.service;

import java.io.IOException;

import com.opencsv.exceptions.CsvException;
import com.pps.payroll.dto.PayrollFileResponseDTO;

/**
 * Interface for payroll file processing services.
 * Defines the contract for processing payroll files.
 */
public interface PayrollFileService {

	/**
     * Processes payroll file located at given file path.
     * @param filePath The path of the payroll file to be processed.
     * @return PayrollFileResponseDTO containing the results of the file processing.
     */
	public PayrollFileResponseDTO processPayrollFile(String filePath) throws IOException, CsvException;
}
