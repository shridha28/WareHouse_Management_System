package com.warehouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * ApiExceptionHandler aop based exception handling.
 * 
 * @author Shridha S Jalihal
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	/*
	 * Method to handle ProductNotFoundException
	 * 
	 * @param ProductNotFoundException productNotFoundException
	 * 
	 * @throws WebRequest request
	 * 
	 * @return ResponseEntity with ErrorResponse
	 */
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse> handleProductNotFoundException(
			ProductNotFoundException productNotFoundException, WebRequest request) {
		return buildErrorResponse(productNotFoundException, productNotFoundException.getMessage(), HttpStatus.NOT_FOUND,
				request);
	}

	/*
	 * Method to handle InvalidJsonFileException
	 * 
	 * @param InvalidJsonFileException invalidJsonFileException
	 * 
	 * @throws WebRequest request
	 * 
	 * @return ResponseEntity with ErrorResponse
	 */
	@ExceptionHandler(InvalidJsonFileException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleInvalidJsonFileException(
			InvalidJsonFileException invalidJsonFileException, WebRequest request) {
		return buildErrorResponse(invalidJsonFileException, invalidJsonFileException.getMessage(),
				HttpStatus.BAD_REQUEST, request);
	}

	/*
	 * Method to handle OutOfStockException
	 * 
	 * @param OutOfStockException outOfStockException
	 * 
	 * @throws WebRequest request
	 * 
	 * @return ResponseEntity with ErrorResponse
	 */
	@ExceptionHandler(OutOfStockException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleOutOfStockException(OutOfStockException outOfStockException,
			WebRequest request) {
		return buildErrorResponse(outOfStockException, outOfStockException.getMessage(),
				HttpStatus.BAD_REQUEST, request);
	}

	/*
	 * Method to build ErrorResponse
	 * 
	 * @param Exception exception
	 * 
	 * @param String message
	 * 
	 * @param HttpStatus httpStatus
	 * 
	 * @param WebRequest request
	 * 
	 * @return ResponseEntity responseEntitty with ErrorResponse
	 */
	private ResponseEntity<ErrorResponse> buildErrorResponse(Exception exception, String message, HttpStatus httpStatus,
			WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), exception.getMessage());
		return ResponseEntity.status(httpStatus).body(errorResponse);
	}

}
