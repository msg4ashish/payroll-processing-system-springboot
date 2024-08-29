package com.pps.payroll.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

/**
 * DTO to hold metadata of input file
 */
public class InputFileMetadataDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Schema(description = "File Path", example = "D:/ppc_files/payroll_data.csv")
	@NotEmpty
	private String payrollFilePath;

	public String getPayrollFilePath() {
		return payrollFilePath;
	}

	public void setPayrollFilePath(String payrollFilePath) {
		this.payrollFilePath = payrollFilePath;
	}	

}
