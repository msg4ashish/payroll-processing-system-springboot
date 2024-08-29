package com.pps.payroll.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pps.payroll.validator.RecordValidator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordValidatorTest {
	
	@Autowired
	RecordValidator validator;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testValidRecords() {
		String[] validRow = new String[] {"1", "emp101", "Bill", "Gates", "SE", "ONBOARD", "1-11-2022", "10-11-2022", "Bill Gates is going to join soon"};
		assertTrue(validator.validateRecord(validRow));
		
		String[] validRowEmpEvent = new String[] {"3", "emp101", "BONUS", "1000", "10-10-2022", "Performance bonus of year 2022"};
		assertTrue(validator.validateRecord(validRowEmpEvent));		
		
	}
	
	@Test
	public void testInvalidRecords() {
		String[] inValidRow = new String[] {"1", "emp101", "Bill", "Gates"};
		assertFalse(validator.validateRecord(inValidRow));
	}
	
	@Test
	public void testInvalidEvent() {
		String[] invalidEventRow = new String[] {"1", "emp101", "Bill", "Gates", "SE", "INVALID", "1-11-2022", "10-11-2022", "Bill Gates is going to join soon"};
		assertFalse(validator.validateRecord(invalidEventRow));
	}
		
	
	
}
