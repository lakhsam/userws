package me.ahmed.projects.jersey.service.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Date;

import me.ahmed.projects.jersey.dto.UserDTO;
import me.ahmed.projects.jersey.dto.UserResponseDTO;
import me.ahmed.projects.jersey.model.User;
import me.ahmed.projects.jersey.model.Usercredential;
import me.ahmed.projects.jersey.repository.UserRepository;
import me.ahmed.projects.jersey.service.OTPService;
import me.ahmed.projects.jersey.service.UserService;
import me.ahmed.projects.jersey.utils.ValidateService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = Logger
			.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ValidateService validateService;

	@Autowired
	private OTPService otpService;

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByEmailaddress(username);
	}

	@Override
	public UserResponseDTO save(UserDTO userDTO) {
		UserResponseDTO userRes = new UserResponseDTO();
		if (userDTO == null
				|| !validateService.validateUserRegistrationField(userDTO)) {
			userRes.setCode(510);
			userRes.setText("An empty required field");
			return userRes;
		}
		String result = validateService.validateUserRegisFieldsLength(userDTO);
		if (!"success".equals(result)) {
			userRes.setCode(511);
			userRes.setText(result);
			return userRes;
		}
		User userExist = getUserByUsername(userDTO.getUsername());
		if (userExist != null) {
			userRes.setCode(1001);
			userRes.setText("User already exist");
			return userRes;
		}
		User userToAdd = constructPersistUser(userDTO);
		User userAdded = userRepository.saveAndFlush(userToAdd);
		User userAddedPost = null;
		if (userToAdd.getUsercredential() != null) {
			userAdded.setUsercredential(userToAdd.getUsercredential());
			userAddedPost = userRepository.saveAndFlush(userToAdd);
		}
		if (userAddedPost != null) {
			userRes.setCode(0);
			userRes.setUserId(userAddedPost.getUserid() + "");
			userRes.setSessionKey(otpService.generateOTP());
			userRes.setText("Save Success.");
			return userRes;
		}
		userRes.setCode(1);
		userRes.setText("ERROR GENERIC");
		return userRes;
	}

	private User constructPersistUser(UserDTO userDTO) {
		User user = null;
		if (userDTO.getUserId() != null) {
			user = userRepository.findOne(userDTO.getUserId());

		} else {
			user = new User();
			user.setEmailaddress(userDTO.getUsername());
			user.setCreateddtm(new Date());
			Usercredential userCrd = new Usercredential();
			userCrd.setConsecutivefailedloginattempts(0);
			userCrd.setPassworddigest(Integer.parseInt(userDTO.getPassword()));
			userCrd.setCreateddtm(new Date());
			userCrd.setModifieddtm(new Date());
			user.setUsercredential(userCrd);
		}

		user.setFirstname(userDTO.getFirstname());
		user.setLastname(userDTO.getLastname());
		user.setPhonenumber(userDTO.getPhoneNumber());
		user.setModifieddtm(new Date());
		return user;
	}

	@Override
	public UserResponseDTO update(UserDTO userDTO) {
		return save(userDTO);
	}

	@Override
	public UserDTO getUserById(Long id, String sessionKey) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		UserDTO userDTO = new UserDTO();
		if (!validateService.validateSessionKey(id,sessionKey)) {
			LOGGER.info("SESSION EXPIRED !");
			userDTO.setUserId(-1L);
			return userDTO;
		} else {
			return getUserDTOById(id);
		}
	}

	@Override
	public UserDTO getUserDTOById(Long id) {
		User user = userRepository.findOne(id);
		if (user != null) {
			return new UserDTO(user);
		}
		return null;
	}

}
