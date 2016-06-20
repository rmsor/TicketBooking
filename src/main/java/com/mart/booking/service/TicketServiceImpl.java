package com.mart.booking.service;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mart.booking.dao.BookingDAO;
import com.mart.booking.dao.CustomerDAO;
import com.mart.booking.dao.LevelDAO;
import com.mart.booking.entity.BookingDetails;
import com.mart.booking.entity.BookingType;
import com.mart.booking.entity.Customer;
import com.mart.booking.entity.Level;
import com.mart.booking.entity.SeatHold;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private BookingDAO bookingDAO;

	@Autowired
	private LevelDAO levelDAO;
	
	@Autowired
	private CustomerDAO customerDAO;
	
	/**
	 * 
	 */
	@Override
	public int numSeatsAvailable(Optional<Integer> venueLevel) {
		Map<Integer,Integer> seatsAvailable=getSeatsAvailable();		
		Integer totalSeats=0;		
		if (venueLevel.isPresent()) {
			totalSeats= seatsAvailable.get(venueLevel.get());
		}else{			
			for(Integer availSeats:seatsAvailable.values()){
				totalSeats+=availSeats;
			}
		}		
		return totalSeats;
	}
	
	/**
	 * 
	 */
	@Override
	public SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel,
			String customerEmail) {

		Level mLevel = null;
		Level mxLevel = null;
		if (minLevel.isPresent()) {
			mLevel = levelDAO.getById(minLevel.get());
		}
		if (maxLevel.isPresent()) {
			mxLevel = levelDAO.getById(maxLevel.get());
		}
		
		Customer customer=customerDAO.getByEmail(customerEmail);
		if(customer==null){
			customer=new Customer();
			customer.setEmail(customerEmail);
			customerDAO.add(customer);
		}
		
		
		
		SeatHold booking = new SeatHold();
		booking.setBookingType(BookingType.BOOKED);
		booking.setBookedDate(new Date());
		booking.setCustomer(customer);
		
		
		List<Level> levels=levelDAO.list();
		
		Set<BookingDetails> bookingDetails=new HashSet<BookingDetails>();
		BookingDetails bookingDetail1=new BookingDetails();
		bookingDetail1.setBookedSeats(10);
		bookingDetail1.setLevel(levels.get(0));
		bookingDetail1.setSeatHold(booking);
		bookingDetail1.setTotalPrice(levels.get(0).getPrice()*10);
		bookingDetails.add(bookingDetail1);
		
		BookingDetails bookingDetail2=new BookingDetails();
		bookingDetail2.setBookedSeats(5);
		bookingDetail2.setLevel(levels.get(1));
		bookingDetail2.setSeatHold(booking);
		bookingDetail2.setTotalPrice(levels.get(1).getPrice()*5);
		bookingDetails.add(bookingDetail2);
		
		booking.setGrandTotal(bookingDetail1.getTotalPrice()+bookingDetail2.getTotalPrice());
		
		booking.setBookingDetails(bookingDetails);
		
		if (bookingDAO.add(booking) > 0) {
			return booking;
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 */
	@Override
	public String reserveSeats(int seatHoldId, String customerEmail) {

		SeatHold booking= makeReservation(seatHoldId,customerEmail);
		
		if(booking!=null){
			return booking.getReservationCode();
		}else{
			return null;
		}
	}
	
	/**
	 * 
	 */
	@Override
	public SeatHold makeReservation(Integer seatHoldId, String customerEmail){
		
		SeatHold booking=bookingDAO.getById(seatHoldId);
		
		if(booking!=null && booking.getBookingType().equals(BookingType.BOOKED)){			
			booking.setBookingType(BookingType.RESERVED);
			bookingDAO.update(booking);			
			return booking;
		}
		
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	private Map<Integer,Integer> getSeatsAvailable(){
		Map<Integer,Integer> seatsBooked=bookingDAO.getSeatsBooked();		
		Map<Integer,Integer> availableSeats=new HashMap<Integer,Integer>();		
		List<Level> levels=levelDAO.list();		
		for(Level level: levels){
			Integer totalSeatsInRow=level.getSeatsInRow()*level.getNoOfRows();
			Integer bookedSeats=0;			
			if(seatsBooked.size()>0 && seatsBooked.containsKey(level.getLevelId())){
				bookedSeats=seatsBooked.get(level.getLevelId());
			}
			availableSeats.put(level.getLevelId(),(totalSeatsInRow-bookedSeats));
		}		
		return availableSeats;
	}

}