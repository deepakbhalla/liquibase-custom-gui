package com.example.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@AllArgsConstructor
@ToString
public class OverviewException extends RuntimeException {
	 
	private static final long serialVersionUID = -6890835156978169878L;

	final HttpStatus httpStatus;
	final String error;
	final String details;
}
