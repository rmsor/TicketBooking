package com.mart.booking.dao;

import java.util.List;

import com.mart.booking.entity.Customer;

public interface CustomerDAO {
	
	/**
	 * get Customer by ID
	 * @param id
	 * @return Customer
	 */
	Customer getById(Integer id);
	
	/**
	 * returns list of all customers
	 * @return List<Customer>
	 */
	List<Customer> list();
	
	/**
	 * get customer by email
	 * @param email Customer email
	 * @return Customer object
	 */
	Customer getByEmail(String email);
	
	/**
	 * adds new Customer
	 * @param Customer object
	 * @return customerId
	 */
	Long add(Customer cust);
}
