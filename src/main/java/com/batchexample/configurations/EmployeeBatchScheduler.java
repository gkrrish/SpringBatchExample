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
    @Qualifier("employeeModificationJob")
    private Job employeeModificationJob; // Assuming the Job is named "employeeModificationJob"

    @Scheduled(cron = "0 0/1 * * * ?") // Runs every hour (replace with your desired schedule)
    public void runEmployeeModificationJob() throws Exception {
        JobExecution execution = jobLauncher.run(employeeModificationJob, new JobParametersBuilder().toJobParameters());
        System.out.println("Job Execution Status: " + execution.getStatus());
    }
}
