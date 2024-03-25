package com.batchexample.configurations;

import java.math.BigDecimal;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.batchexample.billingjob.EmployeeBatchItemWriter;
import com.batchexample.billingjob.EmployeeItemProcessor;
import com.batchexample.entity.Employee;
import com.batchexample.service.EmployeeService;

@Configuration
@EnableBatchProcessing
public class EmployeeBatchConfig {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private EmployeeItemProcessor employeeItemProcessor;

	@Autowired
	private DataSource dataSource;

	@Autowired
	@Qualifier("transactionManager")
	private PlatformTransactionManager transactionManager;

	@SuppressWarnings({ "removal" })
	@Bean
	Job employeeModificationJob(JobBuilderFactory job, Step step1) {
		return job.get("employeeModificationJob").start(step1).build();
	}

	/*
	 * @SuppressWarnings({ "removal" })
	 * 
	 * @Bean Step step1(StepBuilderFactory step, ItemReader<List<Employee>> reader,
	 * ItemProcessor<? super List<Employee>, ? extends List<Employee>> processor,
	 * ItemWriter<List<Employee>> writer) {
	 * 
	 * return step.get("step1") .<List<Employee>, List<Employee>>chunk(100)
	 * .reader(reader) .processor(processor) .writer(writer) .build();
	 * 
	 * }
	 */

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<List<Employee>, List<Employee>>chunk(100)
				.processor(employeeItemProcessor) // Set the ItemProcessor here
				.writer(writer()).build();
	}

	/*
	 * @Bean public Step step1(ItemReader<List<Employee>> reader,
	 * ItemWriter<List<Employee>> writer) { return stepBuilderFactory.get("step1")
	 * .transactionManager(transactionManager) .<List<Employee>,
	 * List<Employee>>chunk(100) .reader(reader) .writer(writer) .build(); }
	 */

	@Bean
	public List<Employee> reader() throws Exception {
		return employeeService.getAllEmployees(100);
	}

	@Bean
	public ItemWriter<List<Employee>> writer() {
		return new EmployeeBatchItemWriter(dataSource);
	}

	/*
	 * @Bean
	 * 
	 * @StepScope public ItemReader<Employee> reader(EmployeeService
	 * employeeService) { return new EmployeeBatchItemReader(); }
	 */

	@Bean(name = "employeeProcessor")
	ItemProcessor<Employee, Employee> processor() {

		return employee -> {
			employee.setName(employee.getName().toUpperCase());
			employee.setBeforeValue(new BigDecimal(3.00));
			employee.setAfterValue(new BigDecimal(4.00));
			return employee;
		};

	}

	/*
	 * @Bean ItemWriter<List<Employee>> writer(DataSource dataSource) { return new
	 * EmployeeBatchItemWriter(); }
	 */
}
