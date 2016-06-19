package com.mart.booking.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class SeatHold {
	
	@Id
	private Long bookingId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date bookedDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date reservedDate;
	
	private Double grandTotal;
	
	@Enumerated(EnumType.ORDINAL)
	private BookingType bookingType;

	@OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="customer", referencedColumnName="customerId")
	private Customer customer;
	
	@OneToMany(mappedBy = "seatHold",cascade=CascadeType.ALL)
	private Set<BookingDetails> bookingDetails=new HashSet<BookingDetails>(0);

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Date getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(Date bookedDate) {
		this.bookedDate = bookedDate;
	}

	public Date getReservedDate() {
		return reservedDate;
	}

	public void setReservedDate(Date reservedDate) {
		this.reservedDate = reservedDate;
	}

	public Double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public BookingType getBookingType() {
		return bookingType;
	}

	public void setBookingType(BookingType bookingType) {
		this.bookingType = bookingType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Set<BookingDetails> getBookingDetails() {
		return bookingDetails;
	}

	public void setBookingDetails(Set<BookingDetails> bookingDetails) {
		this.bookingDetails = bookingDetails;
	};

	
	
	
}
