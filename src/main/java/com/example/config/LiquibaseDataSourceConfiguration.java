package com.example.config;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class LiquibaseDataSourceConfiguration {
	
	private static final Logger LOG = LoggerFactory.getLogger(LiquibaseDataSourceConfiguration.class);
	
	/**
	 * Configuration properties to configure SpringLiquibase.
	 */
	@Autowired
	private LiquibasePropertiesWrapper liquibaseDataSourceProperties;
	
	/**
	 * Qualifier annotation for a DataSource to be injected in to Liquibase. If used for a second data source,
	 * the other (main) one would normally be marked as @Primary.
	 * 
	 * Return ds - DataSource
	 */
	@LiquibaseDataSource
	@Bean
	public DataSource liquibaseDataSource() {
		
		DataSource ds = DataSourceBuilder.create()
				.username(liquibaseDataSourceProperties.getUser())
				.password(liquibaseDataSourceProperties.getPassword())
				.url(liquibaseDataSourceProperties.getUrl())
				.build();
		
		/* The HikariCP pooled DataSource */
		if (ds instanceof HikariDataSource) {
			
			/*
			 * The property controls the maximum size that the pool is allowed to reach, including both idle and in-use
			 * connections. Basically this value will determine the maximum number of actual connections to the datbase
			 * backend. When the pool reaches this size, and no idle connections are available, calls to getConnections()
			 * will block for up to connectionTimeout milliseconds before timing out. 
			 */
			((HikariDataSource) ds).setMaximumPoolSize(2);
			
			/* Set the name of the connection pool to uniquely identify the pool configuration. */
			((HikariDataSource) ds).setPoolName("Liquibase Pool");
		}
		
		LOG.info("-- Initialized data source for the Liquibase successfully. --");
		return ds;
	}

}
