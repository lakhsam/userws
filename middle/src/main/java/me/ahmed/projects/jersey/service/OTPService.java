package me.ahmed.projects.jersey.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

public interface OTPService {

	public String generateOTP(long duration, int digit);

	public String generateOTP();

	public String generateHachOTP(Long id, String username)
			throws InvalidKeyException, SignatureException,
			NoSuchAlgorithmException;

	String generateHachOTP(Long id) throws InvalidKeyException,
			SignatureException, NoSuchAlgorithmException;

}
