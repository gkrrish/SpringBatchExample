package com.batchexample.listners;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;

import com.batchexample.entity.Employee;

public class JobReaderListener implements ItemReadListener<List<Employee>> {

	private static final Logger log = LoggerFactory.getLogger(JobReaderListener.class);

	@Override
	public void beforeRead() {
		log.info(":: Started Reading the data from EmployeeRepository ");
	}

	@Override
	public void afterRead(List<Employee> item) {
		log.info(":: Red the Employee data from  EmployeeRepository");
	}

	@Override
	public void onReadError(Exception ex) {
		log.info(":: Error Reading the Employee data " + ex.getMessage());
	}

}
