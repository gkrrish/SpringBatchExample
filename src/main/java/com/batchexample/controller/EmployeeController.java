package com.batchexample.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.batchexample.entity.Employee;
import com.batchexample.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/{id}")
	@ResponseStatus
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Optional<Employee> employeeOptional = employeeService.getEmployeeById(id);
		if (employeeOptional.isPresent()) {
			return ResponseEntity.ok(employeeOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/100records")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> insert100RandomEmployees() {
		employeeService.insertRandomEmployees(100);
		return ResponseEntity.ok().build();
	}

}
