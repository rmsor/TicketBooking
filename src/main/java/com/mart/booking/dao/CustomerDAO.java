package com.mart.booking.dao;

import java.util.List;

import com.mart.booking.entity.Customer;

public interface CustomerDAO {
	
	Customer getById(Integer id);
	
	List<Customer> list();
	
	Customer getByEmail(String email);
	
	Long add(Customer cust);
}
