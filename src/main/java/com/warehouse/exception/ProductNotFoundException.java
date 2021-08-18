package com.warehouse.exception;


/**
 * Runtime ProductNotFoundException for Product Not Found
 * 
 * @author Shridha S Jalihal
 */
public class ProductNotFoundException extends RuntimeException {
	

	public ProductNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}


	public ProductNotFoundException(String message) {
		super(message);
	}

}
