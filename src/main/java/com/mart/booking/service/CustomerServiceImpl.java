package com.mart.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mart.booking.dao.BookingDAO;
import com.mart.booking.dao.CustomerDAO;
import com.mart.booking.entity.Customer;
import com.mart.booking.entity.SeatHold;

/**
 * customer service
 * 
 * @author rmsor
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {
	
	
	@Autowired
	CustomerDAO customerDAO;
	
	@Autowired
	BookingDAO bookingDAO;
	
	/**
	 * gets Customer by email address
	 * @param email
	 * @return Customer
	 */
	@Override
	public Customer getCustomerByEmail(String email) {
		return customerDAO.getByEmail(email);
	}
	
	/**
	 * get booking made by customer
	 * @param customer
	 * @return List<SeatHold> bookings
	 */
	@Override
	public List<SeatHold> getBookingByCustomer(Customer customer){
		return bookingDAO.getBookingByCustomer(customer);
	}
	

}
