package com.pps.payroll.mapper;

import java.time.LocalDate;

import com.pps.payroll.dto.EmployeeEventRecord;
import com.pps.payroll.dto.OnboardEmployeeRecord;
import com.pps.payroll.entity.Employee;
import com.pps.payroll.entity.EmployeeEvent;
import com.pps.payroll.enums.*;

/**
 * Mapper file to map array record to DTO object
 * Uses the field configuration defined in Enums for mapping
 */
public class PayrollRecordMapper {

	/**
     * Mapper method to map array record to OnboardEmployeeRecord DTO object
     * Uses the field configuration defined in OnboardEmployeeRecordFields Enum for mapping
     * @param row
     * @return
     */
	public static OnboardEmployeeRecord mapToOnboardEmployeeRecord(String[] row) {
        return new OnboardEmployeeRecord(
                row[OnboardEmployeeRecordFields.SEQUENCE_NUMBER.getFieldPosition()],
                row[OnboardEmployeeRecordFields.EMPLOYEE_ID.getFieldPosition()],
                row[OnboardEmployeeRecordFields.FIRST_NAME.getFieldPosition()],
                row[OnboardEmployeeRecordFields.LAST_NAME.getFieldPosition()],
                row[OnboardEmployeeRecordFields.DESIGNATION.getFieldPosition()],
                row[OnboardEmployeeRecordFields.EVENT.getFieldPosition()],
                row[OnboardEmployeeRecordFields.EVENT_VALUE.getFieldPosition()],
                row[OnboardEmployeeRecordFields.EVENT_DATE.getFieldPosition()],
                row[OnboardEmployeeRecordFields.NOTES.getFieldPosition()]
        );
    }

	/**
     * Mapper method to map array record to EmployeeEventRecord DTO object
     * Uses the field configuration defined in EmployeeEventRecordFields Enum for mapping
     * @param row
     * @return
     */
    public static EmployeeEventRecord mapToEmployeeEventRecord(String[] row) {
        return new EmployeeEventRecord(
                row[EmployeeEventRecordFields.SEQUENCE_NUMBER.getFieldPosition()],
                row[EmployeeEventRecordFields.EMPLOYEE_ID.getFieldPosition()],
                row[EmployeeEventRecordFields.EVENT.getFieldPosition()],
                row[EmployeeEventRecordFields.EVENT_VALUE.getFieldPosition()],
                row[EmployeeEventRecordFields.EVENT_DATE.getFieldPosition()],
                row[EmployeeEventRecordFields.NOTES.getFieldPosition()]
        );
    }
    
    
    public static Employee mapToEmployeeEntity(String[] row) {
    	Employee employeeEntity = new Employee();
    	employeeEntity.setEmployeeId(row[OnboardEmployeeRecordFields.EMPLOYEE_ID.getFieldPosition()]);
    	employeeEntity.setFirstName(row[OnboardEmployeeRecordFields.FIRST_NAME.getFieldPosition()]);
    	employeeEntity.setLastName(row[OnboardEmployeeRecordFields.LAST_NAME.getFieldPosition()]);
    	employeeEntity.setDesignation(row[OnboardEmployeeRecordFields.DESIGNATION.getFieldPosition()]);
    	employeeEntity.setEventValue(row[OnboardEmployeeRecordFields.EVENT_VALUE.getFieldPosition()]);
    	employeeEntity.setNotes(row[OnboardEmployeeRecordFields.NOTES.getFieldPosition()]);
    	
    	employeeEntity.setCreatedDate(LocalDate.now());
    	return employeeEntity;
    }
    
    public static EmployeeEvent mapToEmployeeEventEntity(String[] row) {
    	EmployeeEvent employeeEvent = new EmployeeEvent();
    	
    	employeeEvent.setEmployeeId(row[EmployeeEventRecordFields.EMPLOYEE_ID.getFieldPosition()]);
    	employeeEvent.setEvent(row[EmployeeEventRecordFields.EVENT.getFieldPosition()]);
    	employeeEvent.setEventValue(row[EmployeeEventRecordFields.EVENT_VALUE.getFieldPosition()]);
    	employeeEvent.setNotes(row[EmployeeEventRecordFields.NOTES.getFieldPosition()]);
    	
    	employeeEvent.setCreatedDate(LocalDate.now());
    	
    	return employeeEvent;
    }

}
