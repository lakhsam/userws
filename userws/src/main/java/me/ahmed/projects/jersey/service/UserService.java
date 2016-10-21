package me.ahmed.projects.jersey.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import me.ahmed.projects.jersey.dto.UserDTO;
import me.ahmed.projects.jersey.dto.UserResponseDTO;
import me.ahmed.projects.jersey.exception.ResponseException;
import me.ahmed.projects.jersey.model.User;

public interface UserService {

	public User getUserByUsername(String username);

	public UserResponseDTO save(UserDTO userDTO)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException;

	public UserResponseDTO update(UserDTO userDTO)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException;

	public UserDTO getUserById(Long id, String sessionKey)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException, ResponseException;

	public UserDTO getUserDTOById(Long id);

	public User save(User user);

}
