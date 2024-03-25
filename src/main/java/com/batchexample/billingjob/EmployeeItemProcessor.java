package com.batchexample.billingjob;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.batchexample.entity.Employee;

@Component
public class EmployeeItemProcessor implements ItemProcessor<List<Employee>, List<Employee>> {
	
	@Override
    public List<Employee> process(List<Employee> employees) throws Exception {
        // Your processing logic here
        return employees;
    }

}
