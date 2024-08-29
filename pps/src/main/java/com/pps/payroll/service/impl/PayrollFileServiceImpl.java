package com.pps.payroll.service.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.opencsv.exceptions.CsvException;
import com.pps.payroll.dto.PayrollFileResponseDTO;
import com.pps.payroll.service.CSVFileProcessorService;
import com.pps.payroll.service.PayrollFileService;

/**
 * Service to process payroll file. Acts as an abstraction for downstream service class 
 * which are more specific and do actual processing
 * Calls corresponding service class which may be different for different type of files
 */
@Service
public class PayrollFileServiceImpl implements PayrollFileService {
	
	private static Logger logger = LogManager.getLogger(PayrollFileServiceImpl.class);
	

	@Autowired
	@Qualifier("csvReaderServiceImpl")
	private CSVFileProcessorService csvReaderServiceImpl;
	
	
	/**
     * Processes the payroll file based on its type.
     * If the file is a CSV, it processes the file and logs the record details.
     * Throws exception, for unsupported file type
     *
     * @param filePath The path of the payroll file to process.
     * @return PayrollFileResponseDTO containing the results of the file processing.
     */
	@Override
	public PayrollFileResponseDTO processPayrollFile(String filePath) throws IOException, CsvException {
		// Check if the file is a CSV file
		if (filePath.endsWith(".csv")) {
			// Call csv file processor service
			csvReaderServiceImpl.processFile(filePath);
			
			// Log details after processed records are processed
			logger.info("Total number of records:" + csvReaderServiceImpl.getTotalRecordsCount());
			logger.info("Number of records:" + (csvReaderServiceImpl.getTotalRecordsCount() - csvReaderServiceImpl.getInvalidRecords().size()));
			logger.info("Number of invalid records:" + csvReaderServiceImpl.getInvalidRecords().size());
			
			// Return response DTO with results
			return prepareResponse(csvReaderServiceImpl.getTotalRecordsCount(), csvReaderServiceImpl.getInvalidRecords().size());
		} 
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file type"); 
		}
	}
	
	/**
     * Prepares response DTO with the file processing results.
     *
     * @param numRecords Total number of records processed.
     * @param invalidRecords Number of invalid records identified during processing.
     * @return PayrollFileResponseDTO contains results.
     */
	private PayrollFileResponseDTO prepareResponse(int numRecords, int invalidRecords) {
		PayrollFileResponseDTO responseDTO = new PayrollFileResponseDTO();
		responseDTO.setSuccess(true);
		responseDTO.setNumTotalRecords(numRecords);
		responseDTO.setNumInvalidRecords(invalidRecords);
		responseDTO.setNumValidRecords(numRecords - invalidRecords);
		
		return responseDTO;
	}
	

}
