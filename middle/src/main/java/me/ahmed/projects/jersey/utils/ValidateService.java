package me.ahmed.projects.jersey.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import me.ahmed.projects.jersey.dto.LoginDTO;
import me.ahmed.projects.jersey.dto.UserDTO;
import me.ahmed.projects.jersey.service.OTPService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidateService {

	private static final Logger LOGGER = Logger
			.getLogger(ValidateService.class);

	@Autowired
	private OTPService otpService;

	public boolean validateLength(String value, int length) {
		return value != null && value.length() <= length;
	}

	public boolean validateLength(int value, int length) {
		return validateLength(value + "", length);
	}

	public boolean validateEmptyField(String value) {
		return value != null && !"".equals(value);
	}

	public boolean validateUserRegistrationField(UserDTO userDTO) {
		boolean valid = validateEmptyField(userDTO.getUsername())
				&& validateEmptyField(userDTO.getPassword())
				&& validateEmptyField(userDTO.getFirstname())
				&& validateEmptyField(userDTO.getLastname())
				&& validateEmptyField(userDTO.getPhoneNumber())
				&& validateEmptyField(userDTO.getAddress1())
				&& validateEmptyField(userDTO.getCity())
				&& validateEmptyField(userDTO.getCodePostal())
				&& validateEmptyField(userDTO.getRegion())
				&& validateEmptyField(userDTO.getCountryCode());
		if (userDTO.getUserTypeCD() == 2) {
			valid = valid
					&& validateEmptyField(userDTO.getDriverLicenseNumber())
					&& validateEmptyField(userDTO.getDriverLicenseExpDate())
					&& validateEmptyField(userDTO
							.getDriversLicenseIssuedRegion())
					&& validateEmptyField(userDTO.getsSN())
					&& validateEmptyField(userDTO
							.getEmployeePaymentAccountNumber());
		}
		return valid;
	}

	public String validateUserAuthFieldsLength(LoginDTO loginDTO) {
		String result = "success";

		if (!validateLength(loginDTO.getPassword(), 64)) {
			result = "Parameter password exceeds max length of (64)";
			return result;
		}
		if (!validateLength(loginDTO.getUsername(), 254)) {
			result = "Parameter username exceeds max length of (254)";
			return result;
		}

		return result;
	}

	public String validateUserRegisFieldsLength(UserDTO userDTO) {
		String result = "success";

		if (!validateLength(userDTO.getPassword(), 64)) {
			result = "Parameter password exceeds max length of (64)";
			return result;
		}
		if (!validateLength(userDTO.getUsername(), 254)) {
			result = "Parameter username exceeds max length of (254)";
			return result;
		}
		if (!validateLength(userDTO.getFirstname(), 20)) {
			result = "Parameter firstname exceeds max length of (20)";
			return result;
		}
		if (!validateLength(userDTO.getLastname(), 20)) {
			result = "Parameter lastname exceeds max length of (20)";
			return result;
		}
		if (!validateLength(userDTO.getPhoneNumber(), 10)) {
			result = "Parameter phone number exceeds max length of (10)";
			return result;
		}
		if (!validateLength(userDTO.getUsername(), 254)) {
			result = "Parameter username exceeds max length of (254)";
			return result;
		}
		if (!validateLength(userDTO.getUserTypeCD(), 1)) {
			result = "Parameter user type cd exceeds max length of (1)";
			return result;
		}
		if (!validateLength(userDTO.getAddress1(), 30)) {
			result = "Parameter address 1 exceeds max length of (30)";
			return result;
		}
		if (!validateLength(userDTO.getAddress2(), 30)) {
			result = "Parameter address 2 exceeds max length of (30)";
			return result;
		}
		if (!validateLength(userDTO.getCity(), 30)) {
			result = "Parameter city exceeds max length of (30)";
			return result;
		}
		if (!validateLength(userDTO.getRegion(), 2)) {
			result = "Parameter region exceeds max length of (2)";
			return result;
		}
		if (!validateLength(userDTO.getCountryCode(), 30)) {
			result = "Parameter code country exceeds max length of (30)";
			return result;
		}
		return result;
	}

	public boolean validateSessionKey(Long id ,String sessionKey) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		String sessionGenerated = otpService.generateHachOTP(id) ;
		LOGGER.info("Key generated  : "+sessionGenerated+" Key received : "+sessionKey);
		return sessionKey.equals(sessionGenerated);
	}
}
