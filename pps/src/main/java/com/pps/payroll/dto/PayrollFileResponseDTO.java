package com.pps.payroll.dto;

/**
 * DTO to encapsulate PayrollFile processing response
 */
public class PayrollFileResponseDTO extends ApiResponseDTO {

	private static final long serialVersionUID = 1L;

	private int numTotalRecords;
	private int numInvalidRecords;
	private int numValidRecords;

	public PayrollFileResponseDTO() {

	}

	public int getNumTotalRecords() {
		return numTotalRecords;
	}

	public void setNumTotalRecords(int numTotalRecords) {
		this.numTotalRecords = numTotalRecords;
	}

	public int getNumInvalidRecords() {
		return numInvalidRecords;
	}

	public void setNumInvalidRecords(int numInvalidRecords) {
		this.numInvalidRecords = numInvalidRecords;
	}

	public int getNumValidRecords() {
		return numValidRecords;
	}

	public void setNumValidRecords(int numValidRecords) {
		this.numValidRecords = numValidRecords;
	}

}
