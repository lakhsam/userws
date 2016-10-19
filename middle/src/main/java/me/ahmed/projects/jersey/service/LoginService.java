package me.ahmed.projects.jersey.service;

import me.ahmed.projects.jersey.dto.LoginDTO;
import me.ahmed.projects.jersey.dto.LoginResponseDTO;

public interface LoginService {

	public LoginResponseDTO authenticate(LoginDTO login);

}
