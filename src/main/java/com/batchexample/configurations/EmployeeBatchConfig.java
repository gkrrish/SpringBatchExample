package com.batchexample.configurations;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.batchexample.entity.Employee;
import com.batchexample.items.EmployeeItemProcessor;
import com.batchexample.items.EmployeeItemReader;
import com.batchexample.items.EmployeeItemWriter;
import com.batchexample.service.EmployeeService;

@Configuration
public class EmployeeBatchConfig {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	PlatformTransactionManager transactionManager;

    @Bean
    Job processJob(JobRepository jobRepository, @Qualifier("firstStep") Step firstStep) {
		return new JobBuilder("processJob",jobRepository)
				.flow(firstStep)
				.end()
				.build();
	}

    @Bean(name="firstStep")
    Step firstStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("firstStep", jobRepository)
				.<Employee, Employee>chunk(2, transactionManager)
				.reader(new EmployeeItemReader(employeeService))//without constructor also we can do
				.processor(new EmployeeItemProcessor())
				.writer(new EmployeeItemWriter(employeeService)).build();
	}

}
