package com.pps.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pps.payroll.entity.EmployeeEvent;

@Repository
public interface EmployeeEventRepository extends CrudRepository<EmployeeEvent, Long>, JpaRepository<EmployeeEvent, Long> {

}
