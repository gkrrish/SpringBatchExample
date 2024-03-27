package com.batchexample.configurations;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class EmployeeBatchScheduler {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	@Qualifier("processJob")
	private Job processJob;

	@Scheduled(cron = "0 0/1 * * * ?")
	public void runEmployeeModificationJob() throws Exception {
		JobExecution execution = jobLauncher.run(processJob, new JobParametersBuilder().toJobParameters());
		System.out.println("Job Execution Status: " + execution.getStatus());
	}
}
