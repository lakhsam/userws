package me.ahmed.projects.jersey.service.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import me.ahmed.projects.jersey.service.OTPService;
import me.ahmed.projects.jersey.utils.HmacSignature;
import me.ahmed.projects.jersey.utils.OTP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OTPServiceImpl implements OTPService {

	@Value("${otp.seed}")
	private String seed;
	@Value("${otp.algo}")
	private String algo;
	@Value("${otp.digit}")
	private int digit;
	@Value("${otp.duration}")
	private long duration;

	@Autowired
	private HmacSignature hmacSignature;

	@Override
	public String generateOTP(long duration, int digit) {
		return OTP.generateToken(seed, duration, digit, algo);
	}

	@Override
	public String generateOTP() {
		return OTP.generateToken(seed, duration, digit, algo);
	}

	@Override
	public String generateHachOTP(Long id, String username)
			throws InvalidKeyException, SignatureException,
			NoSuchAlgorithmException {
		String data = username + generateOTP() + id;
		return hmacSignature.calculateHMAC(data);
	}
	
	@Override
	public String generateHachOTP(Long id)
			throws InvalidKeyException, SignatureException,
			NoSuchAlgorithmException {
		String data = generateOTP() + id;
		return hmacSignature.calculateHMAC(data);
	}

}
