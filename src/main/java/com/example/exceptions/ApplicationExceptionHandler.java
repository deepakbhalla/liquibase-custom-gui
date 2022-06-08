package com.example.exceptions;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.models.DatabaseChangeSummary;
import com.example.models.OverviewDetails;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;

@ControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(OverviewException.class)
	public ResponseEntity<OverviewDetails> handleException(OverviewDetails e) {
		List<DatabaseChangeSummary> changeSummary = new ArrayList<>();
		return new ResponseEntity<>(OverviewDetails.builder()
				.executedCount(0L)
				.markRanCount(0L)
				.recentContributor(StringUtils.EMPTY)
				.recentExecutor(StringUtils.EMPTY)
				.lastExecutedOn(null)
				.changeSummary(changeSummary)
				.build(), HttpStatus.OK);
	}
}
