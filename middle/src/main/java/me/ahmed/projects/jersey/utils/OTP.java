package me.ahmed.projects.jersey.utils;

import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class OTP {

	private OTP() {
	}

	private static long t = 0;
	private static long current = new Date().getTime();

	private static byte[] hmac_sha1(String crypto, byte[] keyBytes, byte[] text) {
		try {
			Mac hmac;
			hmac = Mac.getInstance(crypto);
			SecretKeySpec macKey = new SecretKeySpec(keyBytes, "RAW");
			hmac.init(macKey);
			return hmac.doFinal(text);
		} catch (GeneralSecurityException gse) {
			throw new UndeclaredThrowableException(gse);
		}
	}

	private static byte[] hexStr2Bytes(String hex) {
		// Adding one byte to get the right conversion
		// values starting with "0" can be converted
		byte[] bArray = new BigInteger("10" + hex, 16).toByteArray();

		// Copy all the REAL bytes, not the "first"
		byte[] ret = new byte[bArray.length - 1];
		for (int i = 0; i < ret.length; i++)
			ret[i] = bArray[i + 1];
		return ret;
	}

	private static final int[] DIGITS_POWER
	// 0 1 2 3 4 5 6 7 8
	= { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000 };

	public static String generateTOTP(String key, String time,
			String returnDigits) {
		return generateTOTP(key, time, returnDigits, "HmacSHA1");
	}

	public static String generateTOTP256(String key, String time,
			String returnDigits) {
		return generateTOTP(key, time, returnDigits, "HmacSHA256");
	}

	public static String generateTOTP512(String key, String time,
			String returnDigits) {
		return generateTOTP(key, time, returnDigits, "HmacSHA512");
	}

	public static String generateTOTP(String key, String time,
			String returnDigits, String crypto) {
		int codeDigits = Integer.decode(returnDigits).intValue();
		String result = null;
		byte[] hash;

		// Using the counter
		// First 8 bytes are for the movingFactor
		// Complaint with base RFC 4226 (HOTP)
		while (time.length() < 16)
			time = "0" + time;

		// Get the HEX in a Byte[]
		byte[] msg = hexStr2Bytes(time);

		// Adding one byte to get the right conversion
		byte[] k = hexStr2Bytes(key);

		hash = hmac_sha1(crypto, k, msg);

		// put selected bytes into result int
		int offset = hash[hash.length - 1] & 0xf;

		int binary = ((hash[offset] & 0x7f) << 24)
				| ((hash[offset + 1] & 0xff) << 16)
				| ((hash[offset + 2] & 0xff) << 8) | (hash[offset + 3] & 0xff);

		int otp = binary % DIGITS_POWER[codeDigits];

		result = Integer.toString(otp);
		while (result.length() < codeDigits) {
			result = "0" + result;
		}
		return result;
	}

	public static String generateToken(String seed, long duration, int digit,
			String algo) {
		String steps = "0";
		if (current + duration > new Date().getTime()) {
			t = current;
		} else {
			current = new Date().getTime();
			t = current;
		}
		steps = Long.toHexString(t).toUpperCase();
		while (steps.length() < 16)
			steps = "0" + steps;
		return generateTOTP(seed, steps, digit + "", algo);
	}

}