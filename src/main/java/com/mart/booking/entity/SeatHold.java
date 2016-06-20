package com.mart.booking.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
public class SeatHold implements Serializable{
	

	private static final long serialVersionUID = 8877036553392476761L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer bookingId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date bookedDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date reservedDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiredDate;
	
	private Double grandTotal;
	
	@Enumerated(EnumType.STRING)
	private BookingType bookingType;

	@OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="customer", referencedColumnName="customerId")
	private Customer customer;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy = "seatHold",cascade=CascadeType.ALL)
	@JsonManagedReference
	private Set<BookingDetails> bookingDetails=new HashSet<BookingDetails>(0);
	
	private String reservationCode;

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
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

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
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
	}

	public String getReservationCode() {
		return reservationCode;
	}

	public void setReservationCode(String reservationCode) {
		this.reservationCode = reservationCode;
	};	
	
}
