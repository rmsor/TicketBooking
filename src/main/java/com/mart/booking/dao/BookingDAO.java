package com.mart.booking.dao;

import java.util.List;
import java.util.Map;

import com.mart.booking.entity.Customer;
import com.mart.booking.entity.SeatHold;

public interface BookingDAO {
	
	 /**
	  * list all bookings 
	  * @return List<SeatHold> objects
	  */
	 List<SeatHold> list();
	 
	 /**
	  * list all booked and reserved bookings that are not expired 
	  * @return List<SeatHold> object
	  */
	 List<SeatHold> listActiveBooking();
	 
	 /**
	  * adds new booking 
	  * @param booking SeatHold object
	  * @return bookingId
	  */
	 Integer add(SeatHold booking);
	 
	 /**
	  * search booking by ID
	  * @param id
	  * @return SeatHold Object
	  */
	 SeatHold getById(Integer id);
	 
	 
	 /**
	  * updates booking 
	  * @param booking object
	  */
	 void update(SeatHold booking);
	 
	 /**
	  * return map of seatsBooked by level
	  * @return Map<Integer,Integer> bookedSeats
	  */
	 Map<Integer,Integer> getSeatsBooked();
	 
	 /**
	  * get all booking done by customer
	  * 
	  * @param Customer customer object
	  * @return List<SeatHold> objects
	  */
	 List<SeatHold> getBookingByCustomer(Customer customer);
}
