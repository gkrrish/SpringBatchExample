package com.batchexample.items;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.batchexample.entity.Employee;
import com.batchexample.service.EmployeeService;

public class EmployeeItemReader implements ItemReader<List<Employee>> {
	
	private EmployeeService employeeService;

	public EmployeeItemReader(EmployeeService employeeService) {
		this.employeeService=employeeService;
	}

	@Override
	public List<Employee> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return employeeService.readRecords();
	}

}
