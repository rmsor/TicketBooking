package com.mart.booking.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mart.booking.dao.BookingDAO;
import com.mart.booking.dao.CustomerDAO;
import com.mart.booking.entity.Customer;
import com.mart.booking.entity.SeatHold;

public class CustomerServiceImplTest {

	@InjectMocks
	CustomerService customerService;

	@Mock
	CustomerDAO customerDAO;

	@Mock
	BookingDAO bookingDAO;

	@Before
	public void setup() {
		customerService=new CustomerServiceImpl();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetCustomerByEmail(){
		String email="test@gmail.com";
		
		Customer customer=new Customer();
		customer.setAddress("104 Bear Lane Mooresville NC");
		customer.setEmail(email);
				
		when(customerDAO.getByEmail(email)).thenReturn(customer);
		
		Customer customerRes=customerService.getCustomerByEmail(email);
		
		assertNotNull(customerRes);
		assertEquals(customerRes.getEmail(),customer.getEmail());
		assertEquals(customerRes.getAddress(),customer.getAddress());
	}
	
	@Test
	public void testGetCustomerByEmailNull(){
		String email="test@gmail.com";
				
		when(customerDAO.getByEmail(email)).thenReturn(null);
		
		Customer customerRes=customerService.getCustomerByEmail(email);
		
		assertNull(customerRes);
	}
	
	@Test
	public void testGetBookingByEmail(){	
		Customer customer=new Customer();
		
		List<SeatHold> booking=new ArrayList<SeatHold>();
				
		when(bookingDAO.getBookingByCustomer(customer)).thenReturn(booking);
		
		List<SeatHold> bookingRes=customerService.getBookingByCustomer(customer);
		
		assertNotNull(bookingRes);
	}
}
