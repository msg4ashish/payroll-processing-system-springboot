package com.pps.payroll.service.impl;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.pps.payroll.service.CSVFileProcessorService;
import com.pps.payroll.validator.RecordValidator;
import com.pps.payroll.mapper.PayrollRecordMapper;
import com.pps.payroll.ApplicationConstants;
import com.pps.payroll.dto.EmployeeEventRecord;
import com.pps.payroll.dto.OnboardEmployeeRecord;

@Service("csvReaderServiceImpl")
/**
 * Responsible for processing CSV files. Extends BaseFileProcessor which has all common methods
 * Reads CSV file, validates each record, and maps it to the appropriate record class
 */
public class CSVFileProcessorServiceImpl extends BaseFileProcessorServiceImpl implements CSVFileProcessorService {
	

	private static Logger logger = LogManager.getLogger(CSVFileProcessorServiceImpl.class);
	
	@Autowired
	private RecordValidator recordValidator;

	@Override
	/**
     * Loads CSV file from the given path, reads all lines and then processes each record one by one
     * @param filePath
     * @throws CsvException
     * @throws IOException
     */
    public void processFile(String filePath) throws IOException, CsvException {
	    //get fileReader object for further processing
        try (FileReader fileReader = readFile(filePath);
             CSVReader csvReader = new CSVReader(fileReader)) {
            //read all lines
            List<String[]> allLines = csvReader.readAll();

            //process each record one by one
            for(String[] line: allLines) {
                processCSVRecord(line);
            }

            //record number of rows present into file
            totalRecordsCount = allLines.size();
        } catch (CsvException | IOException exception) {
            logger.error("Exception occurred while processing file at {}", filePath, exception);
            throw exception;
        }
    }
	
	
	/**
     * Processes single record from the file.
     * Validates the record and then maps it appropriate record object
     * @param row
     */
    private void processCSVRecord(String[] row) {
        //check if the record is valid or not
        if (RecordValidator.validateRecord(row)) {
            //payroll file basically contains 2 types of records. First type if when an employee gets onboarded
            //(onboard employee record). Second type is when an event happens for an employee. (Employee event record)
            // An event could be bonus, exit, salary etc.
            //In the payroll file, structure of both these different record types is different.
            //For example, onboard employee record has 9 fields while employee event has 6 fields
            //So, we need to find out the record type to map it to appropriate DTO
            if (isOnboardEmployeeEvent(row)) {
                OnboardEmployeeRecord onboardEmployeeRecord = PayrollRecordMapper.mapToOnboardEmployeeRecord(row);
                logger.info(onboardEmployeeRecord.toString());
            } else {
                EmployeeEventRecord employeeEventRecord = PayrollRecordMapper.mapToEmployeeEventRecord(row);
                logger.info(employeeEventRecord.toString());
            }
        } else {
            //add invalid record to list and log the sequence number
            invalidRecords.add(row);
            logger.error("Invalid record at seq number: {}", row[0]);
        }
    }
	
    /**
     * Checks what type of record it is.
     * Currently we are relying on number of fields to identify whether it is onboard employee record or
     * employee event record
     * @param row
     * @return true if its onboard employee record
     */
    private boolean isOnboardEmployeeEvent(String[] row) {
        //TODO see if this can be improved
        if (row.length > ApplicationConstants.MINIMUM_NUM_FIELDS) {
            return true;
        }
        return false;
    }
	

}
