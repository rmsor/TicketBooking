package com.mart.booking.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mart.booking.data.AvailableResponse;
import com.mart.booking.data.BookingResponse;
import com.mart.booking.entity.Customer;
import com.mart.booking.entity.Level;
import com.mart.booking.entity.SeatHold;
import com.mart.booking.exception.BadRequestException;
import com.mart.booking.exception.BookingException;
import com.mart.booking.exception.Error;
import com.mart.booking.exception.ServerErrorException;
import com.mart.booking.param.BookingParams;
import com.mart.booking.param.ReserveParams;
import com.mart.booking.service.CustomerService;
import com.mart.booking.service.LevelService;
import com.mart.booking.service.TicketService;

/**
 * rest controller for booking rest API endpoints
 * @author rpathak
 *
 */
@RestController()
@RequestMapping(value = "/rest/booking")
public class TicketResource {

	private final static Logger logger = LoggerFactory.getLogger(TicketResource.class);

	@Autowired
	TicketService ticketService;

	@Autowired
	LevelService levelService;
	
	@Autowired
	CustomerService customerService;
	
	/**
	 * Get available seats by optional param level
	 * @param level optional
	 * @return ResponseEntity<AvailableResponse>
	 * @throws BadRequestException 
	 */
	@RequestMapping(value = "/seatsavailable/v1_0", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AvailableResponse> numSeatsAvailable(
			@RequestParam(value = "level", required = false) Integer level){
		Long startTime = System.currentTimeMillis();
		
		AvailableResponse availableResponse = new AvailableResponse();

		Optional<Integer> levelOpt = Optional.empty();

		if (level != null && level > 0) {

			Level lvl = levelService.getById(level);

			if (lvl != null) {
				availableResponse.setLevel(lvl.getLevelName());
				levelOpt = Optional.of(level);
			}
		}
		Integer totalSeats = ticketService.numSeatsAvailable(levelOpt);

		availableResponse.setAvailableSeats(totalSeats);

		logger.info("method numSeatsAvailable completed. Execution Time (milliseconds): "
				+ (System.currentTimeMillis() - startTime));

		return new ResponseEntity<AvailableResponse>(availableResponse, HttpStatus.OK);
	}
	
	/**
	 * POST add booking
	 * @param bookingParams
	 * @param bindingResult
	 * @return ResponseEntity<SeatHold>
	 * @throws BadRequestException
	 * @throws ServerErrorException
	 */
	@RequestMapping(value = "/add/v1_0", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SeatHold> holdSeat(@Valid @RequestBody BookingParams bookingParams,
			BindingResult bindingResult) throws BadRequestException, ServerErrorException {

		Long startTime = System.currentTimeMillis();
		SeatHold seatHold = null;

		if (bindingResult.hasErrors()) {
			logger.debug("Bad Requst for Add Booking: " + bindingResult.getFieldErrors());
			throw new BadRequestException(bindingResult);
		}
		try {
			seatHold = ticketService.findAndHoldSeats(bookingParams.getNumSeats(),
				Optional.ofNullable(bookingParams.getMinLevel()), Optional.ofNullable(bookingParams.getMaxLevel()),
				bookingParams.getCustomerEmail());
		} catch (BookingException ex) {
			throw new ServerErrorException(ex.getMessage());
		}

		logger.info("method holdseat completed. Execution Time (milliseconds): "
				+ (System.currentTimeMillis() - startTime));

		return new ResponseEntity<SeatHold>(seatHold, HttpStatus.OK);
	}
	
	/**
	 * PUT reserve existing booking
	 * @param reserveParams
	 * @param bindingResult
	 * @return  ResponseEntity<SeatHold>
	 * @throws BadRequestException
	 * @throws ServerErrorException
	 */
	@RequestMapping(value = "/reserve/v1_0", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SeatHold> reserveBooking(@Valid @RequestBody ReserveParams reserveParams,
			BindingResult bindingResult) throws BadRequestException, ServerErrorException {

		Long startTime = System.currentTimeMillis();
		SeatHold seatHold = null;

		if (bindingResult.hasErrors()) {
			logger.debug("Bad Requst for reserveBooking : " + bindingResult.getFieldErrors());
			throw new BadRequestException(bindingResult);
		}
		 try {
			 seatHold = ticketService.makeReservation(reserveParams.getSeatHoldId(), reserveParams.getCustomerEmail());
		 } catch (BookingException ex) {
			 throw new ServerErrorException(ex.getMessage());
		 }

		logger.info("method reserveBooking completed. Execution Time (milliseconds): "
				+ (System.currentTimeMillis() - startTime));

		return new ResponseEntity<SeatHold>(seatHold, HttpStatus.OK);
	}
	
	/**
	 * Get bookings by customer
	 * @param customerEmail
	 * @return ResponseEntity<BookingResponse>
	 */
	@RequestMapping(value = "/list/v1_0", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookingResponse> getBookingsByCustomer(
			@RequestParam(value = "customerEmail", required = true) String customerEmail) {
		Long startTime = System.currentTimeMillis();

		BookingResponse bookingResponse = new BookingResponse();
		Customer customer =customerService.getCustomerByEmail(customerEmail);	
		
		
		if(customer!=null){
			List<SeatHold> bookings= customerService.getBookingByCustomer(customer);
			bookingResponse.setBookings(bookings);
		}else{
			bookingResponse.addError(new Error("No Customer assosicated with that email Found!!"));
		}
		
		logger.info("method numSeatsAvailable completed. Execution Time (milliseconds): "
				+ (System.currentTimeMillis() - startTime));

		return new ResponseEntity<BookingResponse>(bookingResponse, HttpStatus.OK);
	}

}
