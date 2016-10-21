package me.ahmed.projects.jersey.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.ahmed.projects.jersey.dto.LoginDTO;
import me.ahmed.projects.jersey.dto.UserDTO;
import me.ahmed.projects.jersey.service.OTPService;

@Service
public class ValidateService {

	private static final Logger LOGGER = Logger.getLogger(ValidateService.class);

	@Autowired
	private OTPService otpService;

	public boolean validateLength(String value, int length) {
		return value != null && value.length() <= length;
	}

	public boolean validateLength(long value, int length) {
		return validateLength(value + "", length);
	}

	public boolean validateEmptyField(String value) {
		return value != null && !"".equals(value);
	}

	public boolean validateUserRegistrationField(UserDTO userDTO) {
		boolean valid = validateEmptyField(userDTO.getUsername()) && validateEmptyField(userDTO.getPassword())
				&& validateEmptyField(userDTO.getFirstname()) && validateEmptyField(userDTO.getLastname())
				&& validateEmptyField(userDTO.getPhoneNumber()) && validateEmptyField(userDTO.getAddress1())
				&& validateEmptyField(userDTO.getCity()) && validateEmptyField(userDTO.getCodePostal())
				&& validateEmptyField(userDTO.getRegion()) && validateEmptyField(userDTO.getCountryCode());
		if (userDTO.getUserTypeCD() == 2) {
			valid = valid && validateEmptyField(userDTO.getDriverLicenseNumber())
					&& validateEmptyField(userDTO.getDriverLicenseExpDate())
					&& validateEmptyField(userDTO.getDriversLicenseIssuedRegion())
					&& validateEmptyField(userDTO.getsSN())
					&& validateEmptyField(userDTO.getEmployeePaymentAccountNumber());
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
		if (!validateLength(userDTO.getCodePostal(), 12)) {
			result = "Parameter postal code exceeds max length of (12)";
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
		if (userDTO.getUserTypeCD() == 2) {
			if (!validateLength(userDTO.getDriverLicenseNumber(), 10)) {
				result = "Parameter code driver licese number exceeds max length of (10)";
				return result;
			}
			if (!validateLength(userDTO.getDriverLicenseExpDate(), 8)) {
				result = "Parameter driver licese exp date exceeds max length of (8)";
				return result;
			}
			if (!validateLength(userDTO.getDriversLicenseIssuedRegion(), 30)) {
				result = "Parameter driver licese issued region exceeds max length of (30)";
				return result;
			}
			if (!validateLength(userDTO.getsSN(), 10)) {
				result = "Parameter SSN exceeds max length of (10)";
				return result;
			}
			if (!validateLength(userDTO.getEmployeePaymentAccountNumber(), 64)) {
				result = "Parameter employee payment account number exceeds max length of (64)";
				return result;
			}
		}
		return result;
	}

	public boolean validateSessionKey(Long id, String sessionKey)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		String sessionGenerated = otpService.generateHachOTP(id);
		LOGGER.info("Key generated  : " + sessionGenerated + " Key received : " + sessionKey);
		return sessionKey.equals(sessionGenerated);
	}

	public boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	public boolean validateUsetType(long userType) {
		return userType == 1 || userType == 2;
	}

	public boolean validateUserDetailEmpty(Long id, String sessionKey) {
		return validateEmptyField(sessionKey);
	}

	public String validateUserDetailLength(Long id, String sessionKey) {
		String result = "success";
		if (!validateLength(sessionKey, 64)) {
			result = "Parameter sessionKey exceeds max length of (64)";
			return result;
		}
		return result;
	}

	public String validateUserRegisType(UserDTO userDTO) {
		String reg = "^?\\d+$";
		if (!validRegex(userDTO.getUserTypeCD() + "", reg)) {
			return "Type CD must a number";
		}
		if (!validRegex(userDTO.getDriverLicenseExpDate() + "", reg)) {
			return "Driver license exp date must a number";
		}
		return "success";
	}

	public boolean validRegex(String value, String regex) {
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
		java.util.regex.Matcher m = p.matcher(value);
		return m.matches();
	}
}
