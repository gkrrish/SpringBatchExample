package com.batchexample.listners;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemProcessListener;

import com.batchexample.entity.Employee;

public class JobProcessListener implements ItemProcessListener<List<Employee>, List<Employee>> {

	private static final Logger log = LoggerFactory.getLogger(JobProcessListener.class);

	@Override
	public void beforeProcess(List<Employee> item) {
		log.info(":: Before Processing");

	}

	@Override
	public void afterProcess(List<Employee> item, List<Employee> result) {
		log.info(":: After Processing");
	}

	@Override
	public void onProcessError(List<Employee> item, Exception e) {
		log.info(":: Job Process Error " + e.getMessage());
	}

}
