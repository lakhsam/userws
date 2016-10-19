package me.ahmed.projects.jersey.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import me.ahmed.projects.jersey.exception.CustomIllegalArgumentException;
import me.ahmed.projects.jersey.model.ErrorMessage;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<CustomIllegalArgumentException>{

	@Override
	public Response toResponse(CustomIllegalArgumentException ex) {
		ErrorMessage errorMessage= new ErrorMessage(IllegalArgumentException.class.getSimpleName(),ex.getMessage(), ex.getStatusCode(), "www.adria-bt.com/support");
		return Response.status(Status.OK).
				entity(errorMessage).type(MediaType.APPLICATION_JSON).
				build();
	}

}
