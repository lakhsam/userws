package me.ahmed.projects.jersey.service.impl;

import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import me.ahmed.projects.jersey.dto.LoginDTO;
import me.ahmed.projects.jersey.dto.LoginResponseDTO;
import me.ahmed.projects.jersey.exception.ResponseException;
import me.ahmed.projects.jersey.model.User;
import me.ahmed.projects.jersey.model.Usercredential;
import me.ahmed.projects.jersey.model.Userpasswordresettoken;
import me.ahmed.projects.jersey.repository.UserpasswordresettokenRepository;
import me.ahmed.projects.jersey.service.LoginService;
import me.ahmed.projects.jersey.service.OTPService;
import me.ahmed.projects.jersey.service.UserService;
import me.ahmed.projects.jersey.utils.SendMailService;
import me.ahmed.projects.jersey.utils.ValidateService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Value("${otp.digit}")
	private long digit;

	@Autowired
	private ValidateService validateService;

	@Autowired
	private UserService userService;

	@Autowired
	private OTPService otpService;

	@Autowired
	private SendMailService senderService;

	@Autowired
	private UserpasswordresettokenRepository userTokenService;

	@Override
	public LoginResponseDTO authenticate(LoginDTO loginUser)
			throws ResponseException, InvalidKeyException, SignatureException, NoSuchAlgorithmException {

		LoginResponseDTO loginRes = new LoginResponseDTO();

		if (loginUser == null || !validateService.validateEmptyField(loginUser.getPassword())
				|| !validateService.validateEmptyField(loginUser.getPassword())) {
			throw new ResponseException(510, "An empty required field");
		}
		String result = validateService.validateUserAuthFieldsLength(loginUser);
		if (!"success".equals(result)) {
			throw new ResponseException(511, result);
		}
		User user = userService.getUserByUsername(loginUser.getUsername());
		if (user == null) {
			throw new ResponseException(1001, "No user found with entered email address.");
		}
		Usercredential userCrd = user.getUsercredential();
		int attempCount = user.getUsercredential().getConsecutivefailedloginattempts();
		if (attempCount > 2) {
			throw new ResponseException(1003,
					"User account will be locked after 3 consecutive invalid password attempts.");
		}
		if (loginUser.getUsername().equals(user.getEmailaddress())
				&& loginUser.getPassword().equals(userCrd.getPassworddigest() + "")) {
			userCrd.setConsecutivefailedloginattempts(0);
			loginRes.setCode(0);
			loginRes.setUserId(user.getUserid() + "");
			loginRes.setSessionKey(otpService.generateHachOTP(user.getUserid()));
			loginRes.setText("Login Success.");
			return loginRes;

		} else {
			userCrd.setConsecutivefailedloginattempts(attempCount + 1);
			// user.setUsercredential(userCrd);
			// userService.save(user) ;
			throw new ResponseException(1002, "Login Failed.");
		}
	}

	@Override
	public LoginResponseDTO forgotPassword(String username)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		LoginResponseDTO userRes = new LoginResponseDTO();
		if (!validateService.validateEmptyField(username)) {
			userRes.setCode(510);
			userRes.setText("An empty required field");
			return userRes;
		}
		if (!validateService.validateLength(username, 255)) {
			userRes.setCode(511);
			userRes.setText("Email address length must be less than 255");
			return userRes;
		}
		if (!validateService.isValidEmailAddress(username)) {
			userRes.setCode(1002);
			userRes.setText("Invalid email address");
			return userRes;
		}
		User userExist = userService.getUserByUsername(username);
		if (userExist != null) {
			userRes.setCode(404);
			userRes.setText("User not exist");
			return userRes;
		}

		String token = otpService.generateOTP();
		Userpasswordresettoken userToken = new Userpasswordresettoken();
		userToken.setTemporarypassworddigest(new BigDecimal(token));
		userToken.setCreateddtm(new Date());
		Userpasswordresettoken tokenAdded = userTokenService.save(userToken);
		if (tokenAdded != null) {
			senderService.readyToSendEmail(username, "lakhsamamed@gmail.com", "Reset password",
					"PlZ use this password to reset your password : " + token);
		}

		return null;
	}

}
