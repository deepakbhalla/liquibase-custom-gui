package com.example.controllers;

import java.time.Instant;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.constants.ApplicationConstants;
import com.example.exceptions.OverviewException;
import com.example.models.OverviewDetails;
import com.example.service.DashboardService;

@RestController
@RequestMapping("/dashboard/api/v1")
public class DashboardController {
	
	@Autowired
	DashboardService dashboardService;

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
	

	/**
	 * Heath check endpoint.
	 * 
	 * @return ResponseEntity<String>
	 */
	@GetMapping("/health")
	public ResponseEntity<String> ping() {
		return new ResponseEntity<>(ApplicationConstants.PING_RESPONSE.getMessage(), HttpStatus.OK);
	}
	
	/**
	 * Returns information for the overview section of the Liquibase GUI application.
	 * 
	 * @return ResponseEntity<OverviewDetails>
	 */
	@GetMapping("/overview")
	public ResponseEntity<OverviewDetails> getOverviewDetails() {
		try {
			return new ResponseEntity<>(dashboardService.getOverviewDetails(), HttpStatus.OK);
		} catch (Exception e) {
			throw OverviewException.builder().details(e.getMessage()).httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	/**
	 * Generates a unique liquibase changeset id string to be used for <ID> column of [DATABASECHANGELOG] table.
	 * This is recommended to be used to make every change id unique.
	 * 
	 * @param releaseNumber - Name of the target production release name/number
	 * @param changeSummary - Short change description
	 * @param itemId - Change id e.g., JIRA ID etc.
	 * @return ResponseEntity<String>
	 */
	@GetMapping("/liquibase/changeset-id/release/{release-number}/summary/{change-summary}/item/{item-id}")
	public ResponseEntity<String> generateLiquibaseChangeSetId(
			@PathVariable("release-number") @NotNull @NotEmpty String releaseNumber,
			@PathVariable("change-summary") @NotNull @NotEmpty String changeSummary,
			@PathVariable("item-id") @NotNull @NotEmpty String itemId) {
		
		return new ResponseEntity<>(String.valueOf(Instant.now().toEpochMilli())
				.concat("_").concat(releaseNumber)
				.concat("_").concat(itemId)
				.concat("_").concat(changeSummary), HttpStatus.OK);
 	}
}
