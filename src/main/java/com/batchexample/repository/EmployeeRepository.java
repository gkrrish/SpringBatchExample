package com.batchexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batchexample.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}