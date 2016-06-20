package com.mart.booking.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mart.booking.dao.BookingDAO;
import com.mart.booking.dao.CustomerDAO;
import com.mart.booking.dao.LevelDAO;
import com.mart.booking.entity.BookingDetails;
import com.mart.booking.entity.BookingType;
import com.mart.booking.entity.Customer;
import com.mart.booking.entity.Level;
import com.mart.booking.entity.SeatHold;

/**
 * booking service
 * @author rpathak
 *
 */
@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private BookingDAO bookingDAO;

	@Autowired
	private LevelDAO levelDAO;

	@Autowired
	private CustomerDAO customerDAO;

	private List<Level> levels;

	@Value("#{commonProperties['booking.timeToExpireBooking']}")
	private int timeToExpireBooking;

	@PostConstruct
	public void setLevels() {
		levels = levelDAO.list();
	}

	
	/** returns total number of sets available 
	*
	* @param venueLevel a numeric venue level identifier to limit the search
	* @return the number of tickets available on the provided level
	*/
	@Override
	public int numSeatsAvailable(Optional<Integer> venueLevel) {
		Map<Integer, Integer> seatsAvailable = getSeatsAvailable();
		Integer totalSeats = 0;
		if (venueLevel.isPresent()) {
			totalSeats = seatsAvailable.get(venueLevel.get());
		} else {
			for (Integer availSeats : seatsAvailable.values()) {
				totalSeats += availSeats;
			}
		}
		return totalSeats;
	}

	/**
	* Find and hold the best available seats for a customer
	*
	* @param numSeats the number of seats to find and hold
	* @param minLevel the minimum venue level
	* @param maxLevel the maximum venue level
	* @param customerEmail unique identifier for the customer
	* @return a SeatHold object identifying the specific seats and related	information
	*/
	@Override
	public SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel,
			String customerEmail) {

		int minLvl = 0;
		int maxLvl = 0;

		if (minLevel.isPresent()) {
			Level mnLevel = levelDAO.getById(minLevel.get());
			if (mnLevel != null) {
				minLvl = mnLevel.getLevelId();
			}
		}
		if (maxLevel.isPresent()) {
			Level mxLevel = levelDAO.getById(maxLevel.get());
			if (mxLevel != null) {
				maxLvl = mxLevel.getLevelId();
			}
		}

		Customer customer = customerDAO.getByEmail(customerEmail);
		if (customer == null) {
			customer = new Customer();
			customer.setEmail(customerEmail);
			customerDAO.add(customer);
		}

		SeatHold booking = getAvailableBooking(numSeats, minLvl, maxLvl, customer);

		if (booking != null) {
			if (bookingDAO.add(booking) > 0) {
				expireReservation(booking);
				return booking;
			}
		}
		return null;
	}

	/**
	* Commit seats held for a specific customer
	*
	* @param seatHoldId the seat hold identifier
	* @param customerEmail the email address of the customer to which the seat hold	is assigned
	* @return a reservation confirmation code
	*/
	@Override
	public String reserveSeats(int seatHoldId, String customerEmail) {

		SeatHold booking = makeReservation(seatHoldId, customerEmail);

		if (booking != null) {
			return booking.getReservationCode();
		} else {
			return null;
		}
	}

	/**
	* Commit seats held for a specific customer
	*
	* @param seatHoldId the seat hold identifier
	* @param customerEmail the email address of the customer to which the seat hold	is assigned
	* @return a SeatHold object with all the booking information
	*/
	@Override
	public SeatHold makeReservation(Integer seatHoldId, String customerEmail) {

		SeatHold booking = bookingDAO.getById(seatHoldId);

		if (booking != null && booking.getBookingType().equals(BookingType.BOOKED)) {
			booking.setBookingType(BookingType.RESERVED);
			bookingDAO.update(booking);
			return booking;
		}

		return null;
	}

	/**
	 * no. of seats available by each level
	 * @return Map<Integer, Integer> 
	 */
	private synchronized Map<Integer, Integer> getSeatsAvailable() {
		Map<Integer, Integer> seatsBooked = bookingDAO.getSeatsBooked();
		Map<Integer, Integer> availableSeats = new HashMap<Integer, Integer>();
		for (Level level : levels) {
			Integer totalSeatsInRow = level.getSeatsInRow() * level.getNoOfRows();
			Integer bookedSeats = 0;
			if (seatsBooked.size() > 0 && seatsBooked.containsKey(level.getLevelId())) {
				bookedSeats = seatsBooked.get(level.getLevelId());
			}
			availableSeats.put(level.getLevelId(), (totalSeatsInRow - bookedSeats));
		}
		return availableSeats;
	}

	/**
	 * return booking object based on customer selection
	 * 
	 * @param numSeats the number of seats to find and hold
	 * @param minLevel the minimum venue level
	 * @param maxLevel the maximum venue level
	 * @param customer Customer object
	 * @return
	 */
	private synchronized SeatHold getAvailableBooking(int numSeats, int minLevel, int maxLevel, Customer customer) {

		Map<Integer, Integer> seatsAvailable = getSeatsAvailable();

		int totalSeats = 0;
		for (Integer availSeats : seatsAvailable.values()) {
			totalSeats += availSeats;
		}
		// if no. of seats is less than 1 or totalAvailable Seats are less than
		// seats to be booked
		if (numSeats < 1 || totalSeats < numSeats) {
			return null;
		}

		Set<BookingDetails> bookingDetails = new HashSet<BookingDetails>();
		SeatHold booking = new SeatHold();
		booking.setBookingType(BookingType.BOOKED);
		booking.setBookedDate(new Date());
		booking.setCustomer(customer);

		// create booking detail entities and add to collection
		double grandTotal = 0;
		Map<Integer, Integer> recommendedSeats = recommendSeatsByLevel(seatsAvailable, minLevel, maxLevel, numSeats);
		for (Map.Entry<Integer, Integer> mapEntry : recommendedSeats.entrySet()) {
			int bookedSeats = mapEntry.getValue();
			if (bookedSeats > 0) {
				Level level = findById(mapEntry.getKey());
				double totalPrice = level.getPrice() * bookedSeats;
				BookingDetails bookingDetail = new BookingDetails();
				bookingDetail.setBookedSeats(bookedSeats);
				bookingDetail.setLevel(level);
				bookingDetail.setSeatHold(booking);
				bookingDetail.setTotalPrice(totalPrice);
				bookingDetails.add(bookingDetail);
				grandTotal += totalPrice;
			}
		}
		booking.setGrandTotal(grandTotal);
		booking.setBookingDetails(bookingDetails);

		return booking;
	}

	/**
	 * gets seats from levels one by one based on user selection. 
	 * First checks minimum level and then booking total available seats and so on till maxLevel is reached
	 * 
	 * @param seatsAvailable total available seats by level
	 * @param minLevel the minimum venue level
	 * @param maxLevel the maximum venue level
	 * @param numSeats Seats to be booked
	 * @return Map<Integer, Integer> totalSeats to be booked by each level
	 */
	private Map<Integer, Integer> recommendSeatsByLevel(Map<Integer, Integer> seatsAvailable, int minLevel,
			int maxLevel, int numSeats) {

		Map<Integer, Integer> recommendedSeats = new HashMap<Integer, Integer>();

		int totalBooked = 0;

		// iterate through all the levels that has some seat
		for (Map.Entry<Integer, Integer> mapEntry : seatsAvailable.entrySet()) {
			int levelId = mapEntry.getKey();
			int seatsAvailablePerLevel = mapEntry.getValue();

			// determine whether user wants to book from this level or not
			if ((minLevel > 0 && maxLevel > 0) && (levelId < minLevel || levelId > maxLevel)) {
				continue;
			}

			if (totalBooked == numSeats) {
				break;
			} else if (totalBooked < numSeats) {
				int seatsToBook = 0;
				// determine how much seats are avaialable to book in this level
				if (seatsAvailablePerLevel >= (numSeats - totalBooked)) {
					seatsToBook = numSeats - totalBooked;
				} else if (seatsAvailablePerLevel < (numSeats - totalBooked)) {
					seatsToBook = seatsAvailablePerLevel;
				}
				if (seatsToBook > 0) {
					recommendedSeats.put(levelId, seatsToBook);
				}
				totalBooked += seatsToBook;
				continue;
			}
		}

		return recommendedSeats;
	}

	/**
	 * find level by Id from collection
	 * @param levelId
	 * @return Level
	 */
	private Level findById(int levelId) {
		for (Level level : levels) {
			if (level.getLevelId() == levelId) {
				return level;
			}
		}
		return null;
	}
	
	/**
	 * updates SeatHold object to expire and persist after specifed time
	 * 
	 * @param booking SeatHold object
	 */
	private void expireReservation(SeatHold booking) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.SECOND, timeToExpireBooking);
		Timer timer = new Timer();
		timer.schedule(new ExpireReservation(booking), c.getTime());

	}
	
	/**
	 * Timer Class which does the task to expire Booking
	 * 
	 * @author rpathak
	 *
	 */
	class ExpireReservation extends TimerTask {

		private SeatHold booking;
		
		/**
		 * 
		 * @param booking
		 */
		public ExpireReservation(SeatHold booking) {
			this.booking = booking;
		}
		
		/**
		 * do the update task
		 */
		public void run() {
			booking.setExpiredDate(new Date());
			booking.setBookingType(BookingType.EXPIRED);
			bookingDAO.update(booking);
		}
	}

}
