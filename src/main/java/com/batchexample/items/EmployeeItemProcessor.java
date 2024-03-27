package com.batchexample.items;

import java.math.BigDecimal;

import org.springframework.batch.item.ItemProcessor;

import com.batchexample.entity.Employee;

public class EmployeeItemProcessor implements ItemProcessor<Employee, Employee> {
	

	@Override
	public Employee process(Employee employee) throws Exception {
		
		employee.setName(employee.getName().toUpperCase());
		employee.setBeforeValue(new BigDecimal(3.00));
		employee.setAfterValue(new BigDecimal(4.00));
		return employee;
	}

}
