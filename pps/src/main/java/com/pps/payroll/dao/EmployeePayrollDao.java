package com.pps.payroll.dao;

import com.pps.payroll.entity.Employee;
import com.pps.payroll.entity.EmployeeEvent;

public interface EmployeePayrollDao {
	
	Employee addEmployee(Employee employee);
	EmployeeEvent addEmployeeEvent(EmployeeEvent employeeEvent);
	
}
