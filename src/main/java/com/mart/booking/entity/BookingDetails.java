package com.mart.booking.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * entity class for BOOKINGDETAILS table
 * @author rpathak
 *
 */
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BookingDetails implements Serializable{
	
	private static final long serialVersionUID = 7292342103855640431L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonIgnore
	private Integer bookingDetailsId;
	
	@OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="level", referencedColumnName="levelId")
	private Level level;
	
	private Integer bookedSeats;
	
	private Double totalPrice;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "bookingId")
	@JsonBackReference
	private SeatHold seatHold;

	public Integer getBookingDetailsId() {
		return bookingDetailsId;
	}

	public void setBookingDetailsId(Integer bookingDetailsId) {
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
