package com.pps.payroll.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.opencsv.exceptions.CsvException;
import com.pps.payroll.service.CSVFileProcessorService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CSVFileRecordProcessorTest {
	
	@Autowired
	CSVFileProcessorService csvFileProcessorService;

    private static final Logger logger = LogManager.getLogger(CSVFileRecordProcessorTest.class);
    private String filePath = "C:\\Personal\\Projects\\PPCFile.csv";

    @Test
    public void testReadCSVFile() {
        try {
        	csvFileProcessorService.processFile(filePath);
        	assertEquals(5, csvFileProcessorService.getTotalRecordsCount().intValue());
            assertEquals(1, csvFileProcessorService.getInvalidRecords().size());

        } catch (IOException e) {
            logger.error(e);
        } catch (CsvException e) {
            logger.error(e);
        }
    }

    @Test
    public void testNonExistentFile() throws IOException, CsvException {
        String filePath = "C:\\Personal\\Projects\\Invalid.csv";
        Assertions.assertThrows(FileNotFoundException.class, () -> csvFileProcessorService.processFile(filePath));
    }

}
