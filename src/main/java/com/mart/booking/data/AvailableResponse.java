package com.mart.booking.data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * response class for available seats
 * @author rpathak
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AvailableResponse extends BaseResponse implements Serializable{

	private static final long serialVersionUID = 7916013197700724559L;
	
	private Integer availableSeats;
	
	private String level;
	
	/**
	 * 
	 * @return availableSeats
	 */
	public Integer getAvailableSeats() {
		return availableSeats;
	}
	
	/**
	 * 
	 * @param availableSeats
	 */
	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}
	
	/**
	 * 
	 * @return level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * 
	 * @param level
	 */
	public void setLevel(String level) {
		this.level = level;
	}

}
