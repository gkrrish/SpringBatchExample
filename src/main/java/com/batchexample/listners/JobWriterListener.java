package com.batchexample.listners;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.Chunk;

import com.batchexample.entity.Employee;

public class JobWriterListener implements ItemWriteListener<List<Employee>> {

	public static final Logger logger = LoggerFactory.getLogger(JobWriterListener.class);

	@Override
	public void beforeWrite(Chunk<? extends List<Employee>> items) {
		logger.info("Writing started list : " + items);
	}

	@Override
	public void afterWrite(Chunk<? extends List<Employee>> items) {
		logger.info("Writing completed list : " + items);
	}

	@Override
	public void onWriteError(Exception exception, Chunk<? extends List<Employee>> items) {
		logger.error("Error in reading the  records " + items);
		logger.error("Error in reading the records " + exception.getMessage());
	}

}
