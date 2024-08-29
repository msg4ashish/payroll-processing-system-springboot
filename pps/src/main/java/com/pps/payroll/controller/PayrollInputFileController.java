package com.pps.payroll.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.opencsv.exceptions.CsvException;
import com.pps.payroll.ApplicationConstants;
import com.pps.payroll.dto.InputFileMetadataDTO;
import com.pps.payroll.dto.PayrollFileResponseDTO;
import com.pps.payroll.utils.CommonUtils;
import com.pps.payroll.service.PayrollFileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping(ApplicationConstants.API_BASE_URL)
public class PayrollInputFileController {
	
	private static Logger logger = LogManager.getLogger(PayrollInputFileController.class);
	
	@Autowired
	private PayrollFileService payrollFileService;
	
	
	@Operation(summary = "Process Input File", description = "Payroll file processing API")
	@ApiResponse(responseCode = "200", description = "Success", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = InputFileMetadataDTO.class)))
	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
	@PostMapping(ApplicationConstants.API_FILE_PROCESS_URL)
	public ResponseEntity<PayrollFileResponseDTO> processCsvFile(@Valid @RequestBody InputFileMetadataDTO inputFileMetaDataDTO) throws Exception {
		try {
			//fail fast if file does not exists 
			if (!CommonUtils.checkFileExists(inputFileMetaDataDTO.getPayrollFilePath())) {
				logger.error("File not found at given path:" + inputFileMetaDataDTO.getPayrollFilePath());
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File Not Found"); 
			}
			
			//call payroll file processing service
			PayrollFileResponseDTO responseDTO = payrollFileService.processPayrollFile(inputFileMetaDataDTO.getPayrollFilePath());
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);
			
		} catch (IOException | CsvException e) {
			logger.error("Error occured while reading file:" + inputFileMetaDataDTO.getPayrollFilePath(), e);
			throw e;
		}
	}

}
