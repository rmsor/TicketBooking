package com.mart.booking.exception;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.mart.booking.constants.BookingServiceConstants;

/**
 * Exception representing an internal server error
 *  @author rpathak
 */
@XmlRootElement
public class ServerErrorException extends BaseErrorException {

	private static final long serialVersionUID = -6763545411641067033L;

	/**
	 * Constructs a new ServerErrorException with the provided error message
	 * 
	 * @param message
	 */
	public ServerErrorException(String message) {
		super(message);
		errors.add(new Error(BookingServiceConstants.GENERIC_ERROR, message));
	}

	/**
	 * Constructs a new ServerErrorException with the provided error code and
	 * message
	 * 
	 * @param message
	 */
	public ServerErrorException(String code, String message) {
		super(message);
		errors.add(new Error(code, message));
	}

	/**
	 * Constructs a new ServerErrorException with the provided error message and
	 * errors
	 * 
	 * @param message
	 * @param errorList
	 */
	public ServerErrorException(String message, List<Error> errorList) {
		super(message);
		if (!addErrors(errorList)) {
			errors.add(new Error(BookingServiceConstants.GENERIC_ERROR, message));
		}
	}

	/**
	 * Constructs a new ServerErrorException with the provided error message and
	 * exception
	 * 
	 * @param message
	 */
	public ServerErrorException(String message, Throwable throwable) {
		super(message, throwable);
		errors.add(new Error(BookingServiceConstants.GENERIC_ERROR, throwable.getMessage()));
	}

	/**
	 * Constructs a new ServerErrorException with the provided error list
	 * 
	 * @param errors
	 */
	public ServerErrorException(List<Error> errors) {
		super();
		if (errors != null && !errors.isEmpty()) {
			this.errors.addAll(errors);
		}
	}
}
