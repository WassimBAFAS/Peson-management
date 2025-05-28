package com.bfs.crud.rest.controller.advice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bfs.crud.rest.exception.ResourceNotFoundException;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	  @ExceptionHandler(ResourceNotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  @ApiResponse(responseCode = "404", description = "request data not found")
	  public ProblemDetail NotFoundException(ResourceNotFoundException ex) {

	    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
	    problemDetail.setTitle("Resource Not Found");
	    problemDetail.setDetail(ex.getMessage());
	    problemDetail.setProperty("timestamp", LocalDateTime.now());
	    return problemDetail;
	  }

	  @ExceptionHandler(MethodArgumentNotValidException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ApiResponse(responseCode = "400", description = "Invalid request data")
	  public ProblemDetail handleValidationException(MethodArgumentNotValidException ex) {

	    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
	    problemDetail.setTitle("Validation Errors");

	    Map<String, List<String>> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
	        .collect(Collectors.groupingBy(
	            FieldError::getField,
	            Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
	        ));

	    problemDetail.setDetail("Validation failed.");
	    problemDetail.setProperty("fieldErrors", fieldErrors);
	    problemDetail.setProperty("timestamp", LocalDateTime.now());
	    return problemDetail;
	  }

	  // Handle all other uncaught exceptions
	  @ExceptionHandler(Exception.class)
	  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	  @ApiResponse(responseCode = "500", description = "Internal server error")
	  public ProblemDetail handleGlobalException(Exception ex) {

	    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	    problemDetail.setTitle("Internal Server Error");
	    problemDetail.setDetail("An unexpected error occurred.");
	    problemDetail.setProperty("timestamp", LocalDateTime.now());
	    return problemDetail;
	  }
}
