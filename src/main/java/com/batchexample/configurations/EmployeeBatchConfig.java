package com.batchexample.configurations;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
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
import com.batchexample.listners.JobProcessListener;
import com.batchexample.listners.JobReaderListener;
import com.batchexample.listners.JobSkipListener;
import com.batchexample.listners.JobStatusListerner;
import com.batchexample.listners.JobWriterListener;
import com.batchexample.service.EmployeeService;

@Configuration
public class EmployeeBatchConfig {
	
	//Best reference material : https://docs.spring.io/spring-batch/reference/index.html

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	JobRepository jobRepository;//latest versions by default repo, see structure of the tables at test folder
	
	@Autowired
	PlatformTransactionManager transactionManager;

    @Bean(name="processJob")
    Job processJob(JobRepository jobRepository, @Qualifier("firstStep") Step firstStep) {
		return new JobBuilder("processJob",jobRepository)
				.incrementer(new RunIdIncrementer())//every job running time creates Primary key of job for tracing purpose
				.listener(new JobStatusListerner())//Job running time it will be called, there printing logs
				.start(firstStep)
				.preventRestart()
				.build();
	}
    
    //.start(stepA) for some flow we can implement this along we can write for conditions also
	//.next(stepB)
	//.next(stepC)

    @Bean(name="firstStep")
    Step firstStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("firstStep", jobRepository)
				.<List<Employee>, List<Employee>>chunk(1, transactionManager)
				.reader(new EmployeeItemReader(employeeService))
				.processor(new EmployeeItemProcessor())
				.writer(new EmployeeItemWriter(employeeService))
				.readerIsTransactionalQueue()//to make the transaction
				.listener(new JobReaderListener())
				.listener(new JobProcessListener())
				.listener(new JobWriterListener())
				.listener(new JobSkipListener())
				.faultTolerant()
				.retryLimit(3)//retrying for 3 times still occurs, skipping
				.skipPolicy(null)//write skip policy later
				.build();
	}

}
