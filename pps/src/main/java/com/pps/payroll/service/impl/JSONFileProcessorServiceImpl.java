package com.pps.payroll.service.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pps.payroll.service.JSONFileProcessorService;

@Service("JSONFileServiceImpl")
public class JSONFileProcessorServiceImpl extends BaseFileProcessorServiceImpl implements JSONFileProcessorService {

	private static Logger logger = LogManager.getLogger(JSONFileProcessorServiceImpl.class);
	
	@Override
	public void processFile(String filePath) throws IOException {
		logger.info("Processing JSON file");
		
	}
	

}
