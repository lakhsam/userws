package me.ahmed.projects.jersey.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import me.ahmed.projects.jersey.model.ErrorMessage;

import com.sun.jersey.api.NotFoundException;

//@Provider
public class NoResourcesFoundMapper implements
		ExceptionMapper<NotFoundException> {

	public Response toResponse(NotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage(
				NotFoundException.class.getSimpleName(), "RESOURCES NOT FOUND",
				404, "www.adria-bt.com/support");
		return Response.status(Response.Status.NOT_FOUND).entity(errorMessage)
				.type(MediaType.APPLICATION_JSON).build();
	}
}