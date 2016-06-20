package com.mart.booking.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mart.booking.constants.BookingServiceConstants;

/**
 * custom error class
 * @author rpathak
 *
 */
public class Error {
	
	@JsonProperty
	private final String code;
	
	@JsonProperty
	private final String message;
	
	/**
	 * initialize Error
	 * @param message
	 */
	public Error(String message){
		super();
		this.code=BookingServiceConstants.GENERIC_ERROR;
		this.message=message;
	}
	
	/**
	 * initialize Error
	 * @param code
	 * @param message
	 */
	public Error(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	/**
	 * 
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
	
	
	
}
