package com.batchexample.items;

import java.util.List;

import org.springframework.batch.item.ItemReader;

import com.batchexample.entity.Employee;
import com.batchexample.service.EmployeeService;

public class EmployeeItemReader implements ItemReader<List<Employee>> {

	private final EmployeeService employeeService;
	private boolean batchProcessed = false;

	public EmployeeItemReader(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Override
	public List<Employee> read() throws Exception {
		if (!batchProcessed) {
			batchProcessed = true;
			return employeeService.readRecords();
		}
		return null;
	}
}
/*
 * If Reader side data Available means, keep on Batch process will run, so some point we need to stop the Reader Data. 
 */
