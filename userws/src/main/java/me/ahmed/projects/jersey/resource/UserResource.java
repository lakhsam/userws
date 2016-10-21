package me.ahmed.projects.jersey.resource;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import me.ahmed.projects.jersey.dto.BaseResponseDTO;
import me.ahmed.projects.jersey.dto.UserDTO;
import me.ahmed.projects.jersey.dto.UserResponseDTO;
import me.ahmed.projects.jersey.exception.ResponseException;
import me.ahmed.projects.jersey.service.UserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class UserResource {

	private static final Logger LOGGER = Logger.getLogger(UserResource.class);

	@Autowired
	private UserService userService;

	@POST
	@Path("/save")
	public UserResponseDTO save(UserDTO userDTO)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		LOGGER.info("SAVE  USER  " + userDTO.getUsername());
		return userService.save(userDTO);
	}

	@POST
	@Path("/update")
	public UserResponseDTO update(UserDTO userDTO)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		LOGGER.info("UPDATE  USER  " + userDTO.getUsername());
		return userService.update(userDTO);
	}

	@GET
	@Path("/{idUser}")
	public Object get(@PathParam("idUser") Long id, @QueryParam("sessionKey") String sessionKey)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		try {
			LOGGER.info("Get  USER  By Id");
			return userService.getUserById(id, sessionKey);
		} catch (ResponseException e) {
			LOGGER.info("ERROR == Get  USER  By Id " + e.getMessage());
			BaseResponseDTO res = new BaseResponseDTO(e.getCode(), e.getMessage());
			return res;
		}
	}

}
