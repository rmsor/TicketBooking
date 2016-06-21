package com.mart.booking.data;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mart.booking.entity.SeatHold;

/**
 * response class for listing booking
 * @author rmsor
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingResponse extends BaseResponse implements Serializable{

	private static final long serialVersionUID = 7916013197700724559L;
	
	@JsonProperty("bookings")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	List<SeatHold> bookings;
	
	/**
	 * 
	 * @return bookings
	 */
	public List<SeatHold> getBookings() {
		return bookings;
	}
	
	/**
	 * 
	 * @param bookings
	 */
	public void setBookings(List<SeatHold> bookings) {
		this.bookings = bookings;
	}	

}
