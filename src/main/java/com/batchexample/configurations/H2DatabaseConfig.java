package com.batchexample.configurations;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class H2DatabaseConfig {
	// Initially I am loading the 100 records to perform the spring-batch testing

	/*
	 * @Bean
	 * 
	 * @DependsOn("dataSource") public JdbcTemplate jdbcTemplate(DataSource
	 * dataSource) { JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	 * jdbcTemplate.execute("RUNSCRIPT FROM 'classpath:data.sql'"); return
	 * jdbcTemplate; }
	 */

}
