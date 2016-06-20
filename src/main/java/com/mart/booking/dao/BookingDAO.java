package com.mart.booking.dao;

import java.util.List;
import java.util.Map;

import com.mart.booking.entity.SeatHold;

public interface BookingDAO {
	
	
	 List<SeatHold> list();
	 
	 
	 List<SeatHold> listActiveBooking();
	 
	 
	 Integer add(SeatHold booking);
	 
	 
	 SeatHold getById(Integer id);
	 
	 
	 void update(SeatHold booking);
	 
	 
	 Map<Integer,Integer> getSeatsBooked();
	 
}
