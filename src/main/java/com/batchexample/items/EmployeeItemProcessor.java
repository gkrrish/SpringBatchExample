package com.batchexample.items;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemProcessor;

import com.batchexample.entity.Employee;

public class EmployeeItemProcessor implements ItemProcessor<List<Employee>, List<Employee>> {

	@Override
	public List<Employee> process(List<Employee> employees) throws Exception {

		List<Employee> returnEmployeeList = employees.stream().map(employee -> {
			employee.setBeforeValue(new BigDecimal(7.00));
			employee.setAfterValue(new BigDecimal(8.00));
			employee.setName(employee.getName().toUpperCase());
			return employee;
		}).collect(Collectors.toList());

		return returnEmployeeList;
	}

}
