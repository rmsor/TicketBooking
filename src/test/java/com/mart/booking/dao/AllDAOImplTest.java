package com.mart.booking.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.mart.booking.entity.BookingType;
import com.mart.booking.entity.Customer;
import com.mart.booking.entity.Level;
import com.mart.booking.entity.SeatHold;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
@WebAppConfiguration
public class AllDAOImplTest {

	
	@Autowired
	private BookingDAO bookingDAO;

	@Autowired
	private LevelDAO levelDAO;

	@Autowired
	private CustomerDAO customerDAO;
	
	@Test
    @Transactional
    @Rollback(true)
    public void testBookingDAOlist()
    {
		List<SeatHold> bookings=bookingDAO.list();
        assertNotNull(bookings);
    }
	
	@Test
    @Transactional
    @Rollback(true)
    public void testBookingDAOlistActiveBooking()
    {
		List<SeatHold> bookings=bookingDAO.listActiveBooking();
        assertNotNull(bookings);
    }
	
	@Test
    @Transactional
    @Rollback(true)
    public void testBookingDAOaddBooking()
    {	
		SeatHold booking = new SeatHold();
		booking.setBookingType(BookingType.BOOKED);
		booking.setBookedDate(new Date());
		
		String email="ram@gmail.com";
		
		Customer customer = customerDAO.getByEmail(email);
		if (customer == null) {
			customer = new Customer();
			customer.setEmail(email);
			customerDAO.add(customer);
		}
		
		booking.setCustomer(customer);
		
		int bookingId=bookingDAO.add(booking);
        assertTrue(bookingId>0);
        
        SeatHold addedBooking=bookingDAO.getById(bookingId);
        
        assertNotNull(addedBooking);
        assertEquals(BookingType.BOOKED,addedBooking.getBookingType());
        
    }
	
	@Test
    @Transactional
    @Rollback(true)
    public void testBookingDAOgetSeatsBooked()
    {	
		Map<Integer,Integer> bookedSeats=bookingDAO.getSeatsBooked();
        assertNotNull(bookedSeats);
    }
	
	
	@Test
    @Transactional
    @Rollback(true)
    public void testBookingDAOgetBookingByCustomer()
    {	
		String email="ram@gmail.com";
		Customer customer = customerDAO.getByEmail(email);
		if (customer == null) {
			customer = new Customer();
			customer.setEmail(email);
			customerDAO.add(customer);
		}
		
		List<SeatHold> bookings=bookingDAO.getBookingByCustomer(customer);
        assertNotNull(bookings);
    }
	
	@Test
    @Transactional
    @Rollback(true)
    public void testCustomerDAOlist()
    {
		List<Customer> customers=customerDAO.list();
        assertNotNull(customers);
    }
	
	@Test
    @Transactional
    @Rollback(true)
    public void testCustomerDAOgetById()
    {
		String email="ram@gmail.com";
		
		Customer customer = new Customer();
		customer.setEmail(email);
		customerDAO.add(customer);
		
		int customerID=customerDAO.add(customer);
        assertTrue(customerID>0);
		
		
    }
	
	@Test
    @Transactional
    @Rollback(true)
    public void testLevelDAOlist()
    {
		List<Level> levels=levelDAO.list();
        assertNotNull(levels);	
        assertEquals(levels.size(),4);
		
    }
	
	@Test
    @Transactional
    @Rollback(true)
    public void testLevelDAOgetById()
    {
		Level Level=levelDAO.getById(1);
        assertNotNull(Level);	
        assertEquals(1,Level.getLevelId().intValue());
		
    }
	
}
