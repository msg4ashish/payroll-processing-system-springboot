package com.pps.payroll;

/**
 * Class to hold all application specific constants
 */
public class ApplicationConstants {
	
	//base url
	public static final String API_BASE_URL = "/api/v1";

	public static final String API_FILE_PROCESS_URL = "/payroll/process";
	
	//Minimum number of fields a record should have for it be valid. 
	//Change here, if it increases or decreases further
	public static final int MINIMUM_NUM_FIELDS = 6;

}
