package com.batchexample.items;

import java.util.List;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import com.batchexample.entity.Employee;
import com.batchexample.service.EmployeeService;

public class EmployeeItemWriter implements ItemWriter<List<Employee>> {
	int intial=0;
	private EmployeeService employeeService;

	public EmployeeItemWriter(EmployeeService employeeService) {
		this.employeeService=employeeService;
	}

	@Override
	public void write(Chunk<? extends List<Employee>> employees) throws Exception {
		System.out.println("WRITE: "+intial);
		intial++;
		employeeService.save(employees);
		
	}

}
