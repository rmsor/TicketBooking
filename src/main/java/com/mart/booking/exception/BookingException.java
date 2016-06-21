package com.mart.booking.exception;


/**
 * 
 * @author rmsor
 *
 */
public class BookingException extends Exception {

	private static final long serialVersionUID = -7623108453389969197L;

	/**
	 * Constructs a new BookingException with the provided error message
	 * 
	 * @param message
	 */
	public BookingException(String message) {
		super(message);
	}
	
	/**
	 * Constructs a new BookingException with the provided error message and
	 * exception
	 * 
	 * @param message
	 */
	public BookingException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
