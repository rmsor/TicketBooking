package com.mart.booking.util;

import java.security.SecureRandom;

/**
 * class to generate random reservation code
 *
 */
public final class RandomReservationCode {

	private static SecureRandom random = new SecureRandom();

	private final static char[] CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z'};

	/**
	 * generates random reservationCode
	 * 
	 * @param length of reservationCode to generate
	 * @return String reservationCode
	 */
	public static String next(int length) {
		StringBuilder reservationCode = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(CHARS.length);
			reservationCode.append(CHARS[randomIndex]);
		}
		
		return reservationCode.toString();
	}
}