package com.example.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

@RestController
@RequestMapping("/liquibase")
public class LiquibaseOperationsController {

	private static final Logger LOG = LoggerFactory.getLogger(LiquibaseOperationsController.class);
	
	@Autowired
	private LiquibaseProperties liquibaseProperties;
	
	/**
	 * Redirects to index page of the application.
	 * 
	 * @return modelAndView - ModelAndView
	 */
	@GetMapping("/home")
	public ModelAndView redirect() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@GetMapping("/run")
	public ResponseEntity<String> updateLiquibase() {
		
		try (Connection c = DriverManager.getConnection(
				liquibaseProperties.getUrl(), 
				liquibaseProperties.getUser(), 
				liquibaseProperties.getPassword())) {
			
			Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(c));
			LOG.info("Liquibase Change log file: {}", liquibaseProperties.getChangeLog());

			try (Liquibase liquibase = new Liquibase(liquibaseProperties.getChangeLog(), new ClassLoaderResourceAccessor(), database)) {
				liquibase.update("main");
			}
			
		} catch (SQLException | LiquibaseException e) {
			throw new NoSuchElementException("Error Details: " + e.getMessage());
		}
		
		return new ResponseEntity<>("Liquibase has been executed succesfully", HttpStatus.OK);
	}
}
