package com.mart.booking.data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mart.booking.exception.Error;

/**
 * base response for errors
 * @author rpathak
 *
 */
public abstract class BaseResponse  {
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<Error> errors=null;
	
	/**
	 * 
	 * @return errors
	 */
	public List<Error> getErrors() {
		return errors;
	}
	
	/**
	 * 
	 * @param errors
	 */

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
	
	/**
	 * generic method to add error
	 * @param err
	 */
	public void addError(Error err){
		if(errors==null){
			errors=new ArrayList<Error>();			
		}
		errors.add(err);
	}
}
