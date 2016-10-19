package me.ahmed.projects.jersey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import me.ahmed.projects.jersey.dto.LoginDTO;
import me.ahmed.projects.jersey.dto.LoginResponseDTO;
import me.ahmed.projects.jersey.model.User;
import me.ahmed.projects.jersey.service.LoginService;
import me.ahmed.projects.jersey.service.OTPService;
import me.ahmed.projects.jersey.service.UserService;
import me.ahmed.projects.jersey.utils.ValidateService;

@Service
public class LoginServiceImpl implements LoginService {

	@Value("${otp.digit}")
	private long digit;

	@Autowired
	private ValidateService validateService;

	@Autowired
	private UserService userService;

	@Autowired
	private OTPService otpService;

	@Override
	public LoginResponseDTO authenticate(LoginDTO loginUser) {

		LoginResponseDTO loginRes = new LoginResponseDTO();

		if (loginUser == null || !validateService.validateEmptyField(loginUser.getPassword())
				|| !validateService.validateEmptyField(loginUser.getPassword())) {
			loginRes.setCode(510);
			loginRes.setText("An empty required field");
			return loginRes;
		}
		String result = validateService.validateUserAuthFieldsLength(loginUser);
		if (!"success".equals(result)) {
			loginRes.setCode(511);
			loginRes.setText(result);
			return loginRes;
		}
		User user = userService.getUserByUsername(loginUser.getUsername());
		if (user == null) {
			loginRes.setCode(1001);
			loginRes.setText("No user found with entered email address.");
			return loginRes;
		}
		if (!loginUser.getUsername().equals(user.getEmailaddress())) {
			loginRes.setCode(1002);
			loginRes.setText("Login Failed.");
			return loginRes;
		}
		if (loginUser.getUsername().equals("ahmed") && loginUser.getPassword().equals("ahmed")) {
			loginRes.setCode(0);
			loginRes.setUserId(user.getUserid() + "");
			loginRes.setSessionKey(otpService.generateOTP());
			loginRes.setText("Login Success.");
			return loginRes;
		}
		return null;
	}

}
