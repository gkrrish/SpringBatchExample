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
			employee.setBeforeValue(new BigDecimal(5.00));
			employee.setAfterValue(new BigDecimal(6.00));
			employee.setName(employee.getName().toLowerCase());
			return employee;
		}).collect(Collectors.toList());

		return returnEmployeeList;
	}

}
