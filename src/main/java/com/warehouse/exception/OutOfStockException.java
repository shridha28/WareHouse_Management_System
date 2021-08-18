package com.warehouse.exception;


/**
 * Runtime OutOfStockException for Stocks UnAvailability
 * 
 * @author Shridha S Jalihal
 */
public class OutOfStockException extends RuntimeException {


	public OutOfStockException(String message, Throwable cause) {
		super(message, cause);
	}


	public OutOfStockException(String message) {
		super(message);
	}


}
