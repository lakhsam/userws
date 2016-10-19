package me.ahmed.projects.jersey.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import me.ahmed.projects.jersey.dto.UserDTO;
import me.ahmed.projects.jersey.dto.UserResponseDTO;
import me.ahmed.projects.jersey.model.User;

public interface UserService {

	public User getUserByUsername(String username);
	
	public UserResponseDTO save(UserDTO userDTO);

	public UserResponseDTO update(UserDTO userDTO);

	public UserDTO getUserById(Long id, String sessionKey) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException;
	
	public UserDTO getUserDTOById(Long id) ;

}
