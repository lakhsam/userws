package me.ahmed.projects.jersey.service.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.ahmed.projects.jersey.dto.UserDTO;
import me.ahmed.projects.jersey.dto.UserResponseDTO;
import me.ahmed.projects.jersey.exception.ResponseException;
import me.ahmed.projects.jersey.model.User;
import me.ahmed.projects.jersey.model.Usercredential;
import me.ahmed.projects.jersey.repository.AddressRepository;
import me.ahmed.projects.jersey.repository.UserRepository;
import me.ahmed.projects.jersey.repository.UserTypeRepository;
import me.ahmed.projects.jersey.service.OTPService;
import me.ahmed.projects.jersey.service.UserService;
import me.ahmed.projects.jersey.utils.ValidateService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserTypeRepository typeRepository;

	@Autowired
	private ValidateService validateService;

	@Autowired
	private OTPService otpService;

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByEmailaddress(username);
	}

	@Override
	public UserResponseDTO save(UserDTO userDTO)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		UserResponseDTO userRes = new UserResponseDTO();
		if (userDTO == null || !validateService.validateUserRegistrationField(userDTO)) {
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
		String result2 = validateService.validateUserRegisType(userDTO);
		if (!"success".equals(result2)) {
			userRes.setCode(512);
			userRes.setText(result2);
			return userRes;
		}
		if (!validateService.validateUsetType(userDTO.getUserTypeCD())) {
			userRes.setCode(514);
			userRes.setText("Type User is not valid choode 1 or 2");
			return userRes;
		}
		if (!validateService.isValidEmailAddress(userDTO.getUsername())) {
			userRes.setCode(1002);
			userRes.setText("Invalid email address");
			return userRes;
		}
		User userExist = getUserByUsername(userDTO.getUsername());
		if (userDTO.getUserId() == null && userExist != null) {
			userRes.setCode(1001);
			userRes.setText("User already exist");
			return userRes;
		}
		User userToAdd = constructPersistUser(userDTO);
		// Address add = addressRepository.save(userToAdd.getAddress()) ;
		// Usertype userTyp = typeRepository.save(userToAdd.getUsertype()) ;
		User userAdded = userRepository.saveAndFlush(userToAdd);
		if(userDTO.getUserId() == null){
			userAdded.setUsercredential(userToAdd.getUsercredential());
			userToAdd.getUsercredential().setUser(userAdded);
			userAdded = userRepository.saveAndFlush(userAdded);
		}
		// userAdded.setAddress(add);
		// userAdded.setUsertype(userTyp);
		
		if (userAdded != null) {
			userRes.setCode(0);
			userRes.setUserId(userAdded.getUserid() + "");
			userRes.setSessionKey(otpService.generateHachOTP(userAdded.getUserid()));
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
			// Usertype userType = new Usertype();
			// userType.setUsertypecd(userDTO.getUserTypeCD());
			// user.setUsertype(userType);
			Usercredential userCrd = new Usercredential();
			userCrd.setConsecutivefailedloginattempts(0);
			userCrd.setPassworddigest(Integer.parseInt(userDTO.getPassword()));
			userCrd.setCreateddtm(new Date());
			userCrd.setModifieddtm(new Date());
			user.setUsercredential(userCrd);
		}
		// Address address = new Address();
		// address.setCity(userDTO.getCity());
		// address.setAddressline1(userDTO.getAddress1());
		// address.setAddressline2(userDTO.getAddress2());
		// address.setCountry(userDTO.getCountryCode());
		// address.setRegion(userDTO.getRegion());
		// user.setAddress(address);

		user.setFirstname(userDTO.getFirstname());
		user.setLastname(userDTO.getLastname());
		user.setPhonenumber(userDTO.getPhoneNumber());
		user.setModifieddtm(new Date());
		return user;
	}

	@Override
	public UserResponseDTO update(UserDTO userDTO)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		return save(userDTO);
	}

	@Override
	public UserDTO getUserById(Long id, String sessionKey)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException, ResponseException {

		if (!validateService.validateUserDetailEmpty(id, sessionKey)) {
			throw new ResponseException(510, "An empty required field");
		}
		String result = validateService.validateUserDetailLength(id, sessionKey);
		if (!"success".equals(result)) {
			throw new ResponseException(511, result);
		}
		UserDTO userDTO = new UserDTO();
		if (!validateService.validateSessionKey(id, sessionKey)) {
			throw new ResponseException(4040, "Session key not valid");
		}
		userDTO = getUserDTOById(id);
		if (userDTO == null) {
			throw new ResponseException(404, "User wth this id not exist");
		}
		return userDTO;
	}

	@Override
	public UserDTO getUserDTOById(Long id) {
		User user = userRepository.findOne(id);
		if (user != null) {
			return new UserDTO(user);
		}
		return null;
	}

	@Override
	public User save(User user) {
		return userRepository.saveAndFlush(user);
	}

}
