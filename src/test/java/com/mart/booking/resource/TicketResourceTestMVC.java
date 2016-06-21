package com.mart.booking.resource;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mart.booking.entity.Customer;
import com.mart.booking.entity.Level;
import com.mart.booking.entity.SeatHold;
import com.mart.booking.service.CustomerService;
import com.mart.booking.service.LevelService;
import com.mart.booking.service.TicketService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
@WebAppConfiguration
public class TicketResourceTestMVC {

	@Autowired
	@InjectMocks
	TicketResource ticketResource;

	@Mock
	TicketService ticketService;

	@Mock
	LevelService levelService;

	@Mock
	CustomerService customerService;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		// Process mock annotations
		MockitoAnnotations.initMocks(this);
		// Setup Spring test
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
	}

	/**
	 * testNumSeatsAvailable
	 * 
	 * @throws Exception
	 */
	@Test
	public void testNumSeatsAvailable() throws Exception {

		Level level = new Level();
		level.setLevelId(1);
		level.setLevelName("TestLevel");

		when(ticketService.numSeatsAvailable(Optional.of(1))).thenReturn(200);
		when(levelService.getById(1)).thenReturn(level);

		mockMvc.perform(get("/rest/booking/seatsavailable/v1_0").param("level", "1")).andExpect(status().isOk());
	}

	@Test
	public void testGetBookingsByCustomer() throws Exception {

		String email = "test@gmail.com";

		Customer customer = new Customer();
		customer.setEmail(email);

		when(customerService.getCustomerByEmail(email)).thenReturn(customer);

		when(customerService.getBookingByCustomer(customer)).thenReturn(new ArrayList<SeatHold>());

		mockMvc.perform(get("/rest/booking/list/v1_0").param("customerEmail", email)).andExpect(status().isOk());
	}
}
