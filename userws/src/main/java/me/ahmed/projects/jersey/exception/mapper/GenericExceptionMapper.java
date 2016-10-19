package me.ahmed.projects.jersey.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import me.ahmed.projects.jersey.exception.GenericException;
import me.ahmed.projects.jersey.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements
		ExceptionMapper<GenericException> {

	@Override
	public Response toResponse(GenericException ex) {
		ErrorMessage errorMessage = new ErrorMessage(
				GenericException.class.getSimpleName(), ex.getMessage(),
				ex.getStatusCode(), "www.adria-bt.com/support");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.type(MediaType.APPLICATION_JSON).entity(errorMessage).build();
	}

}
