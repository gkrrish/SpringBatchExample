package com.batchexample.configurations;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import com.batchexample.entity.Employee;
import com.batchexample.repository.EmployeeRepository;

public class EmployeeBatchItemReader implements ItemReader<Employee> {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return (Employee) employeeRepository.readPage(100);
	}

}
