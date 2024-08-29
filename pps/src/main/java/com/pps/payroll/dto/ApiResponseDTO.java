package com.pps.payroll.dto;

import java.io.Serializable;

/**
 * DTO class to encapsulate API response
 */
public class ApiResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Boolean success;

	private String errorCode;

	private String message;


	public ApiResponseDTO() {
	}

	public ApiResponseDTO(Boolean success) {
		this.success = success;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
