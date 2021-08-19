/**
 * 
 */
package com.warehouse.exception;

/**
 * Runtime InvalidJsonFileException for file parsing exceptions
 * 
 * @author sjalihal
 */
public class InvalidJsonFileException extends RuntimeException {

	public InvalidJsonFileException(String message, Throwable cause) {
		super(message, cause);
	}


	public InvalidJsonFileException(String message) {
		super(message);
	}
}
