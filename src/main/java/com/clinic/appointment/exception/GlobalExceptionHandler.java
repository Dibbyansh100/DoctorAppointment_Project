package com.clinic.appointment.exception;

import java.net.URI;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	   @ExceptionHandler(ResourceNotFoundException.class)
	    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
	        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
	        problemDetail.setTitle("Resource Not Found");
	        problemDetail.setInstance(URI.create(request.getRequestURI()));
	        problemDetail.setProperty("timestamp", Instant.now());
	        return problemDetail;
	    }

	    @ExceptionHandler(DuplicateResourceException.class)
	    public ProblemDetail handleDuplicateResourceException(DuplicateResourceException ex, HttpServletRequest request) {
	        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
	        problemDetail.setTitle("Duplicate Resource");
	        problemDetail.setInstance(URI.create(request.getRequestURI()));
	        problemDetail.setProperty("timestamp", Instant.now());
	        return problemDetail;
	    }

	    @ExceptionHandler(BusinessRuleViolationException.class)
	    public ProblemDetail handleBusinessRuleViolationException(BusinessRuleViolationException ex, HttpServletRequest request) {
	        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
	        problemDetail.setTitle("Business Rule Violation");
	        problemDetail.setInstance(URI.create(request.getRequestURI()));
	        problemDetail.setProperty("timestamp", Instant.now());
	        return problemDetail;
	    }

	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
	        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
	                HttpStatus.BAD_REQUEST,
	                "Validation failed for one or more request fields."
	        );
	        problemDetail.setTitle("Validation Failed");
	        problemDetail.setInstance(URI.create(request.getRequestURI()));
	        problemDetail.setProperty("timestamp", Instant.now());

	        Map<String, String> fieldErrors = new LinkedHashMap<>();
	        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
	            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
	        }
	        problemDetail.setProperty("errors", fieldErrors);
	        return problemDetail;
	    }

	    @ExceptionHandler(HandlerMethodValidationException.class)
	    public ProblemDetail handleHandlerMethodValidationException(HandlerMethodValidationException ex, HttpServletRequest request) {
	        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
	                HttpStatus.BAD_REQUEST,
	                "Request parameter validation failed."
	        );
	        problemDetail.setTitle("Validation Failed");
	        problemDetail.setInstance(URI.create(request.getRequestURI()));
	        problemDetail.setProperty("timestamp", Instant.now());
	        return problemDetail;
	    }

	    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
	    public ProblemDetail handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
	        String parameterName = ex.getName();
	        String requiredType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown";
	        String message = String.format("Parameter '%s' should be of type '%s'.", parameterName, requiredType);

	        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, message);
	        problemDetail.setTitle("Invalid Parameter Type");
	        problemDetail.setInstance(URI.create(request.getRequestURI()));
	        problemDetail.setProperty("timestamp", Instant.now());
	        return problemDetail;
	    }

	    @ExceptionHandler(HttpMessageNotReadableException.class)
	    public ProblemDetail handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
	        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
	                HttpStatus.BAD_REQUEST,
	                "Malformed JSON request body or unparseable field values."
	        );
	        problemDetail.setTitle("Malformed Request");
	        problemDetail.setInstance(URI.create(request.getRequestURI()));
	        problemDetail.setProperty("timestamp", Instant.now());
	        return problemDetail;
	    }

	    @ExceptionHandler(DataIntegrityViolationException.class)
	    public ProblemDetail handleDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
	        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
	                HttpStatus.CONFLICT,
	                "Database integrity constraint violation occurred."
	        );
	        problemDetail.setTitle("Database Conflict");
	        problemDetail.setInstance(URI.create(request.getRequestURI()));
	        problemDetail.setProperty("timestamp", Instant.now());
	        return problemDetail;
	    }

	    @ExceptionHandler(Exception.class)
	    public ProblemDetail handleGeneralException(Exception ex, HttpServletRequest request) {
	        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
	                HttpStatus.INTERNAL_SERVER_ERROR,
	                "An unexpected internal server error occurred."
	        );
	        problemDetail.setTitle("Internal Server Error");
	        problemDetail.setInstance(URI.create(request.getRequestURI()));
	        problemDetail.setProperty("timestamp", Instant.now());
	        return problemDetail;
	    }

}
