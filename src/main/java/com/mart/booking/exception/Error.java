package com.mart.booking.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mart.booking.constants.BookingServiceConstants;

public class Error {
	
	@JsonProperty
	private final String code;
	
	@JsonProperty
	private final String message;
	
	public Error(String message){
		super();
		this.code=BookingServiceConstants.GENERIC_ERROR;
		this.message=message;
	}
	
	public Error(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	
	
}
