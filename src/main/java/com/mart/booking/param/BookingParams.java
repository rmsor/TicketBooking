package com.mart.booking.param;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * param class to hold booking json request and validate
 * @author rpathak
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingParams implements Serializable{
	
	private static final long serialVersionUID = -6920618628616743119L;
	
	@Valid
	@NotNull(message="{error.booking.numSeats.null}")
	private Integer numSeats;
	
	private Integer minLevel;
	
	private Integer maxLevel;
	
	@Valid
	@NotNull(message="{error.booking.customerEmail.null}")
	private String customerEmail;

	public Integer getNumSeats() {
		return numSeats;
	}

	public void setNumSeats(Integer numSeats) {
		this.numSeats = numSeats;
	}

	public Integer getMinLevel() {
		return minLevel;
	}

	public void setMinLevel(Integer minLevel) {
		this.minLevel = minLevel;
	}

	public Integer getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(Integer maxLevel) {
		this.maxLevel = maxLevel;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	
}
