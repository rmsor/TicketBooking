package com.mart.booking.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.mart.booking.dao.BookingDAO;
import com.mart.booking.dao.CustomerDAO;
import com.mart.booking.dao.LevelDAO;
import com.mart.booking.entity.Customer;
import com.mart.booking.entity.Level;
import com.mart.booking.entity.SeatHold;
import com.mart.booking.exception.BookingException;

/**
 * 
 * @author rmsor
 *
 */
public class TicketServiceImplTest {
	
	@InjectMocks
	TicketService ticketService;

	@Mock
	private BookingDAO bookingDAO;

	@Mock
	private LevelDAO levelDAO;

	@Mock
	private CustomerDAO customerDAO;

	@Before
	public void setup() {
		ticketService=new TicketServiceImpl();
		MockitoAnnotations.initMocks(this);		
		
		ReflectionTestUtils.setField(ticketService,"levels",getTestLevels());
		ReflectionTestUtils.setField(ticketService,"timeToExpireBooking",100);
		ReflectionTestUtils.setField(ticketService,"reservationCodeLength",10);
	}
	
	@Test
	public void testNumSeatsAvailable(){
		
		when(bookingDAO.getSeatsBooked()).thenReturn(getBookedSeats());
		
		int totalAvailable=ticketService.numSeatsAvailable(Optional.of(1));
		
		assertEquals(50,totalAvailable);
		
		totalAvailable=ticketService.numSeatsAvailable(Optional.of(2));
		
		assertEquals(800,totalAvailable);
		
		totalAvailable=ticketService.numSeatsAvailable(Optional.empty());
		
		assertEquals(2050,totalAvailable);
		
		totalAvailable=ticketService.numSeatsAvailable(Optional.of(8));
		
		assertEquals(0,totalAvailable);
	}
	
	
	@Test
	public void testFindAndHoldSeats() throws BookingException{
		
		Level level=new Level();
		level.setLevelId(1);
		level.setLevelName("TestLevel");
		when(levelDAO.getById(1)).thenReturn(level);
		

		Level level1=new Level();
		level1.setLevelId(2);
		level1.setLevelName("TestLeve2");
		when(levelDAO.getById(2)).thenReturn(level1);
		
		String email="test@gmail.com";
		Customer customer=new Customer();
		customer.setEmail(email);
		
		when(customerDAO.getByEmail(email)).thenReturn(customer);
		
		when(bookingDAO.getSeatsBooked()).thenReturn(getBookedSeats());
		
		when(bookingDAO.add(any(SeatHold.class))).thenReturn(100);
		
		Mockito.doNothing().when(bookingDAO).update(any(SeatHold.class));
		
		SeatHold booking=ticketService.findAndHoldSeats(250, Optional.of(1), Optional.of(3),email);
		
		assertNotNull(booking);
		
		assertEquals(booking.getBookingDetails().size(),2);
		
		assertEquals(booking.getBookingDetails().get(0).getBookedSeats().intValue(),50);
		assertEquals(booking.getBookingDetails().get(01).getBookedSeats().intValue(),200);
		
	}
	
	
	private Map<Integer, Integer> getBookedSeats(){
		Map<Integer, Integer> seatsBookedMap = new HashMap<Integer, Integer>();
		seatsBookedMap.put(1,150);
		seatsBookedMap.put(2,200);
		return seatsBookedMap;
	}
	
	
	/**
	 * 
	 * @return List<Level> 
	 */
	private List<Level> getTestLevels(){
		//all seats: 2400
		List<Level> levels=new ArrayList<Level>();
		
		//total seats 200
		Level level1=new Level();
		level1.setLevelId(1);
		level1.setLevelName("testLevel");
		level1.setNoOfRows(10);
		level1.setPrice(100.00);
		level1.setSeatsInRow(20);
		
		levels.add(level1);
		
		//total seats 1000
		Level level2=new Level();
		level2.setLevelId(2);
		level2.setLevelName("testLeve2");
		level2.setNoOfRows(20);
		level2.setPrice(50.00);
		level2.setSeatsInRow(50);
		
		levels.add(level2);
		
		//total seats 1200
		Level level3=new Level();
		level3.setLevelId(3);
		level3.setLevelName("testLeve3");
		level3.setNoOfRows(30);
		level3.setPrice(70.00);
		level3.setSeatsInRow(40);
		
		levels.add(level3);	
		
		
		return levels;
	}
}
