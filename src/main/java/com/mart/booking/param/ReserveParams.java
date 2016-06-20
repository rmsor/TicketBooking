package com.mart.booking.param;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReserveParams implements Serializable{
	
	private static final long serialVersionUID = -6920618628616743119L;
	
	@Valid
	@NotNull(message="{error.reserve.seatHoldId.null}")
	private Integer seatHoldId;
	
	@Valid
	@NotNull(message="{error.reserve.customerEmail.null}")
	private String customerEmail;
	

	public Integer getSeatHoldId() {
		return seatHoldId;
	}

	public void setSeatHoldId(Integer seatHoldId) {
		this.seatHoldId = seatHoldId;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
}
