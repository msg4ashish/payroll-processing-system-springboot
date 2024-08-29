package com.pps.payroll.repository;

import com.pps.payroll.entity.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>, JpaRepository<Employee, Long> {
	
	Optional<Employee> findEmployeeByEmployeeId(String employeeId);

}
