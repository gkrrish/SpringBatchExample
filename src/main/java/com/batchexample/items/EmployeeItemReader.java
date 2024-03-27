package com.batchexample.items;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.batchexample.entity.Employee;
import com.batchexample.service.EmployeeService;

public class EmployeeItemReader implements ItemReader<Employee> {
	
	private EmployeeService employeeService;

	public EmployeeItemReader(EmployeeService employeeService) {
		this.employeeService=employeeService;
	}

	@Override
	public Employee read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		employeeService.readRecords();
		return null;
	}

}
