package com.mart.booking.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mart.booking.constants.BookingServiceConstants;

@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BadRequestException extends BindException {

	private static final long serialVersionUID = 2985654087063439957L;

	@JsonProperty
	private final List<Error> errors = new ArrayList<Error>();

	public BadRequestException(BindingResult bindingResult) {
		super(bindingResult);

		generateErrors(errors, bindingResult);
	}

	private void generateErrors(List<Error> errorList, BindingResult result) {
		// error pattern for field errors
		Pattern pattern = Pattern.compile(BookingServiceConstants.ERROR_FIELD_REGEX_PATTERN);
		// error pattern for object errors
		Pattern classPattern = Pattern.compile(BookingServiceConstants.ERROR_CLASS_REGEX_PATTERN);

		for (ObjectError error : result.getAllErrors()) {
			// Handle field errors
			if (error instanceof FieldError) {
				FieldError fieldError = (FieldError) error;
				if (pattern.matcher(error.getDefaultMessage()).matches()) {
					String[] errorMessage = error.getDefaultMessage()
							.split(BookingServiceConstants.ERROR_SPLIT_PATTERN);
					errorList.add(new Error(errorMessage[0], String.format(errorMessage[1], fieldError.getField())));
				} else {
					errorList.add(new Error(BookingServiceConstants.GENERIC_ERROR,
							String.format("%s %s", fieldError.getField(), error.getDefaultMessage())));
				}
			} else {
				// Handle object errors
				if (classPattern.matcher(error.getDefaultMessage()).matches()) {
					String[] errorMessage = error.getDefaultMessage()
							.split(BookingServiceConstants.ERROR_SPLIT_PATTERN);
					errorList
							.add(new Error(errorMessage[0], String.format(errorMessage[1], error.getDefaultMessage())));
				} else {
					errorList.add(new Error(BookingServiceConstants.GENERIC_ERROR,
							String.format("%s", error.getDefaultMessage())));
				}
			}
		}
	}

}
