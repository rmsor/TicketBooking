package com.mart.booking.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.mart.booking.data.AvailableResponse;
import com.mart.booking.data.BookingResponse;
import com.mart.booking.entity.Customer;
import com.mart.booking.entity.Level;
import com.mart.booking.entity.SeatHold;
import com.mart.booking.exception.BadRequestException;
import com.mart.booking.exception.BookingException;
import com.mart.booking.exception.ServerErrorException;
import com.mart.booking.param.BookingParams;
import com.mart.booking.param.ReserveParams;
import com.mart.booking.service.CustomerService;
import com.mart.booking.service.LevelService;
import com.mart.booking.service.TicketService;

/**
 * test class for ticket resource
 * @author rmsor
 *
 */
public class TicketResourceTest {
	
	@InjectMocks
	TicketResource ticketResource;

	@Mock
	TicketService ticketService;

	@Mock
	LevelService levelService;
	
	@Mock
	CustomerService customerService;
	
	@Mock
	BindingResult erros;
	
	AvailableResponse availableResponse;
	
	BookingResponse bookingResponse;

	@Before
	public void setup() {
		ticketResource = new TicketResource();
		MockitoAnnotations.initMocks(this);

		availableResponse = new AvailableResponse();
		bookingResponse=new BookingResponse();
	}
	
	
	@Test
	public void testNumSeatsAvailable(){
		
		Level level=new Level();
		level.setLevelId(1);
		level.setLevelName("TestLevel");
		
		when(ticketService.numSeatsAvailable(Optional.of(1))).thenReturn(200);
		when(levelService.getById(1)).thenReturn(level);
		
		ResponseEntity<AvailableResponse> response=ticketResource.numSeatsAvailable(1);
		
		assertNotNull(response);
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
		
		assertEquals(new Integer(200),response.getBody().getAvailableSeats());
	}
	
	
	@Test
	public void testHoldSeat() throws BadRequestException, ServerErrorException, BookingException{
		
		BookingParams bookingParams=new BookingParams();
		bookingParams.setCustomerEmail("test@gmail.com");
		bookingParams.setMaxLevel(3);
		bookingParams.setMinLevel(1);
		bookingParams.setNumSeats(100);
		
		when(ticketService.findAndHoldSeats(bookingParams.getNumSeats(),
				Optional.ofNullable(bookingParams.getMinLevel()), Optional.ofNullable(bookingParams.getMaxLevel()),
				bookingParams.getCustomerEmail())).thenReturn(new SeatHold());
		
		ResponseEntity<SeatHold> response=ticketResource.holdSeat(bookingParams,erros);
		
		assertNotNull(response);
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
	
	@Test(expected=BadRequestException.class)
	public void testHoldSeatBadException() throws BadRequestException, ServerErrorException{
		
		BookingParams bookingParams=new BookingParams();
		
		BindingResult bindingResult=new BeanPropertyBindingResult(bookingParams,"bookingParams");
		bindingResult.addError(new ObjectError("bookingParams","invalid params"));
		
		
		ticketResource.holdSeat(bookingParams,bindingResult);
	}
	
	@Test(expected=ServerErrorException.class)
	public void testHoldSeatServerException() throws BadRequestException, ServerErrorException, BookingException{
		
		BookingParams bookingParams=new BookingParams();
		bookingParams.setCustomerEmail("test@gmail.com");
		bookingParams.setMaxLevel(3);
		bookingParams.setMinLevel(1);
		bookingParams.setNumSeats(100);
		
		when(ticketService.findAndHoldSeats(bookingParams.getNumSeats(),
				Optional.ofNullable(bookingParams.getMinLevel()), Optional.ofNullable(bookingParams.getMaxLevel()),
				bookingParams.getCustomerEmail())).thenThrow(new BookingException("Test Error"));
		
		ticketResource.holdSeat(bookingParams,erros);
	}
	
	
	@Test
	public void testReserveBooking() throws BadRequestException, ServerErrorException, BookingException{
		
		ReserveParams reserveParams=new ReserveParams();
		reserveParams.setCustomerEmail("test@gmail.com");
		reserveParams.setSeatHoldId(3);
		
		when(ticketService.makeReservation(reserveParams.getSeatHoldId(), reserveParams.getCustomerEmail())).thenReturn(new SeatHold());
		
		ResponseEntity<SeatHold> response=ticketResource.reserveBooking(reserveParams,erros);
		
		assertNotNull(response);
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
	
	
	@Test(expected=BadRequestException.class)
	public void testReserveBookingBadException() throws BadRequestException, ServerErrorException{
		
		ReserveParams reserveParams=new ReserveParams();
		
		BindingResult bindingResult=new BeanPropertyBindingResult(reserveParams,"reserveParams");
		bindingResult.addError(new ObjectError("reserveParams","invalid params"));
		
		
		ticketResource.reserveBooking(reserveParams,bindingResult);
	}
	
	@Test(expected=ServerErrorException.class)
	public void testReserveBookingServerException() throws BadRequestException, ServerErrorException, BookingException{
		
		ReserveParams reserveParams=new ReserveParams();	
		
		when(ticketService.makeReservation(null, null)).thenThrow(new BookingException("Test Error"));
		
		ticketResource.reserveBooking(reserveParams,erros);
	}
	
	
	@Test
	public void testGetBookingsByCustomer(){
		
		String email="test@gmail.com";
		
		Customer customer=new Customer();
		customer.setEmail(email);
		
		when(customerService.getCustomerByEmail(email)).thenReturn(customer);
		
		when(customerService.getBookingByCustomer(customer)).thenReturn(new ArrayList<SeatHold>());
		
		ResponseEntity<BookingResponse> response=ticketResource.getBookingsByCustomer(email);
		
		assertNotNull(response);
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
}
