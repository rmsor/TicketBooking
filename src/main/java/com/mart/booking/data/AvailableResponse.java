package com.mart.booking.data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AvailableResponse extends BaseResponse implements Serializable{

	private static final long serialVersionUID = 7916013197700724559L;
	
	private Integer avilableSeats;
	
	private String level;

	public Integer getAvilableSeats() {
		return avilableSeats;
	}

	public void setAvilableSeats(Integer avilableSeats) {
		this.avilableSeats = avilableSeats;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
