package com.mart.booking.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class RandomReservationCodeTest {
	
	@Test
	public void testRandomString(){
		String string1=RandomReservationCode.next(15);
		
		String string2=RandomReservationCode.next(15);
		
		assertNotNull(string1);
		
		assertNotNull(string2);
		
		assertFalse(string1.equals(string2));
		
		assertEquals(string1.length(),15);
		
		assertEquals(string2.length(),15);
	}
}
