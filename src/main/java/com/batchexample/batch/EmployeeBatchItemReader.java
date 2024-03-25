package com.batchexample.batch;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.batchexample.entity.Employee;
import com.batchexample.repository.EmployeeRepository;

@Component
public class EmployeeBatchItemReader implements ItemReader<List<Employee>> {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> read() throws Exception {
		return employeeRepository.readPage(100);
	}

}
