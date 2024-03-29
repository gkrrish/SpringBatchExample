package com.batchexample.listners;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.SkipListener;

import com.batchexample.entity.Employee;

public class JobSkipListener implements SkipListener<List<Employee>, List<Employee>> {

	public static final Logger logger = LoggerFactory.getLogger(JobSkipListener.class);
	private int skipCount = 0;

	@Override
	public void onSkipInRead(Throwable t) {
		logger.info("Skipped during reading: " + t.getMessage());
	}

	@Override
	public void onSkipInWrite(List<Employee> item, Throwable t) {
		logger.info("Skipped during writing: " + item + ", error: " + t.getMessage());
		skipCount++;
	}

	@Override
	public void onSkipInProcess(List<Employee> item, Throwable t) {
		logger.info("Skipped during processing: " + item + ", error: " + t.getMessage());
		skipCount++;
	}

	public int getSkipCount() {
		return skipCount;
	}

}
