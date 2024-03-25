package com.batchexample.billingjob;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import com.batchexample.entity.Employee;

public class EmployeeBatchItemWriter implements ItemWriter<List<Employee>>{
	private DataSource dataSource;

    public EmployeeBatchItemWriter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void write(List<? extends List<Employee>> items) throws Exception {
        // Implement your write logic here
    }

	@Override
	public void write(Chunk<? extends List<Employee>> chunk) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
