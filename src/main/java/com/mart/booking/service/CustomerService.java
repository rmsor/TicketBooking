package com.mart.booking.service;

import java.util.List;

import com.mart.booking.entity.Customer;
import com.mart.booking.entity.SeatHold;

public interface CustomerService {
	
	/**
	 * gets Customer by email address
	 * @param email
	 * @return Customer
	 */
	Customer getCustomerByEmail(String email);
	
	/**
	 * get booking made by customer
	 * @param customer
	 * @return List<SeatHold> bookings
	 */
	List<SeatHold> getBookingByEmail(Customer customer);
}
