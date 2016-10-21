package me.ahmed.projects.jersey.resource;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.ahmed.projects.jersey.dto.LoginDTO;
import me.ahmed.projects.jersey.dto.LoginResponseDTO;
import me.ahmed.projects.jersey.exception.ResponseException;
import me.ahmed.projects.jersey.service.LoginService;
import me.ahmed.projects.jersey.service.OTPService;

@Path("login")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class LoginResource {

	private static final Logger LOGGER = Logger.getLogger(LoginResource.class);

	@Autowired
	private LoginService loginService;

	@Autowired
	private OTPService otpService;

	@POST
	@Path("/authenticate")
	public LoginResponseDTO autenticate(LoginDTO login)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		LOGGER.info("LOGIN FOR USER  " + login.getUsername());
		try {
			return loginService.authenticate(login);
		} catch (ResponseException e) {
			return new LoginResponseDTO(e.getCode(), e.getMessage(), "", "");
		}
	}

	@GET
	@Path("/sessionKey/{idUser}")
	public String getSessionKey(@PathParam("idUser") Long id)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		LOGGER.info("Get  USER session key");
		return otpService.generateHachOTP(id);
	}
	
	@POST
	@Path("/forgotPassword")
	public LoginResponseDTO forgotPassword(LoginDTO login)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		LOGGER.info("Forgot password for  : " + login.getUsername());
		return loginService.forgotPassword(login.getUsername());
	}
	
	

}
