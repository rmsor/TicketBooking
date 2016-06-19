package com.mart.booking.domain;

import javax.persistence.Column;
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
	
	@Column(name = "levelId")
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "BookingDetails")
	private Level level;
	
	private Integer bookedSeats;
	
	private Double totalPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bookingId", nullable = false)
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
