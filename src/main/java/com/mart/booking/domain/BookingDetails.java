package com.mart.booking.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class BookingDetails {
	
	@Id
	private int bookingDetailsId;
	
	@OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="level", referencedColumnName="levelId")
	private Level level;
	
	private Integer bookedSeats;
	
	private Double totalPrice;

	@ManyToOne
	@JoinColumn(name = "bookingId")
	private SeatHold seatHold;

	public int getBookingDetailsId() {
		return bookingDetailsId;
	}

	public void setBookingDetailsId(int bookingDetailsId) {
		this.bookingDetailsId = bookingDetailsId;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Integer getBookedSeats() {
		return bookedSeats;
	}

	public void setBookedSeats(Integer bookedSeats) {
		this.bookedSeats = bookedSeats;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public SeatHold getSeatHold() {
		return seatHold;
	}

	public void setSeatHold(SeatHold seatHold) {
		this.seatHold = seatHold;
	}
	
	
	
	
}
