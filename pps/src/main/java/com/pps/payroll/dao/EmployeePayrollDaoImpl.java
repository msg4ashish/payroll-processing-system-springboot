package com.pps.payroll.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pps.payroll.entity.Employee;
import com.pps.payroll.entity.EmployeeEvent;
import com.pps.payroll.repository.EmployeeEventRepository;
import com.pps.payroll.repository.EmployeeRepository;

@Component
public class EmployeePayrollDaoImpl implements EmployeePayrollDao {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeEventRepository employeeEventRepository;

	@Override
	public Employee addEmployee(Employee employee) {
		final Optional<Employee> existingEmployee = employeeRepository.findEmployeeByEmployeeId(employee.getEmployeeId());
		if (existingEmployee.isPresent()) {
			employee.setId(existingEmployee.get().getId());
		}
		return employeeRepository.save(employee);
	}

	@Override
	public EmployeeEvent addEmployeeEvent(EmployeeEvent employeeEvent) {
		return employeeEventRepository.save(employeeEvent);
	}

}
