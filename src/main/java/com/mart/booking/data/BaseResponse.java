package com.mart.booking.data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mart.booking.exception.Error;

public abstract class BaseResponse  {
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<Error> errors=null;

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
	
	public void addError(Error err){
		if(errors==null){
			errors=new ArrayList<Error>();			
		}
		errors.add(err);
	}
}
