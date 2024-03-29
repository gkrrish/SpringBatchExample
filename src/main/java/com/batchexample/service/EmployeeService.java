package com.batchexample.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.batch.item.Chunk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.batchexample.batch.EmployeeBatchItemReader;
import com.batchexample.entity.Employee;
import com.batchexample.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Optional<Employee> getEmployeeById(Long id) {
		return employeeRepository.findById(id);
	}

	// -----initial loading purpose-----------------

	public void insertRandomEmployees(int count) {
		for (int i = 0; i < count; i++) {
			Employee employee = generateRandomEmployee(i);
			employeeRepository.save(employee);
		}
	}

	private Employee generateRandomEmployee(int i) {
		Employee employee = new Employee();
		employee.setName("Name " + i);
		employee.setAge((int) (Math.random() * 60) + 18);
		employee.setMobileNumber("+91" + 123456678 + i);
		employee.setBeforeValue(new BigDecimal(1).setScale(2));
		employee.setAfterValue(new BigDecimal(2).setScale(2));
		return employee;
	}

	public List<Employee> readRecords() {
		return Optional.ofNullable(employeeRepository.findAll()).orElse(Collections.emptyList());
	}
	int initial=0;

	public void save(Chunk<? extends List<Employee>> employees) {
		System.out.println("SERVICE:WRITE: "+initial);
		initial++;
		List<Employee> employeeList = new ArrayList<>();
		for (List<Employee> emp : employees) {
			employeeList.addAll(emp);
		}
		
		employeeRepository.saveAll(employeeList);
	}

}
