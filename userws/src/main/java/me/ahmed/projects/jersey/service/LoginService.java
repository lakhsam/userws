package me.ahmed.projects.jersey.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import me.ahmed.projects.jersey.dto.LoginDTO;
import me.ahmed.projects.jersey.dto.LoginResponseDTO;
import me.ahmed.projects.jersey.exception.ResponseException;

public interface LoginService {

	public LoginResponseDTO authenticate(LoginDTO login)
			throws ResponseException, InvalidKeyException, SignatureException, NoSuchAlgorithmException;

	public LoginResponseDTO forgotPassword(String username) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException;

}
