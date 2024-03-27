package com.batchexample.configurations;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
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

	@Scheduled(cron = "0 0/15 * * * ?")
	public void runProcessJob() throws Exception {
		JobParameters jobParameters=new JobParametersBuilder()
										.addLong("dateTime", System.currentTimeMillis())
										.toJobParameters();
		JobExecution execution = jobLauncher.run(processJob, jobParameters);
		System.out.println("Job Execution Status: " + execution.getStatus());
	}
}
