package me.ahmed.projects.jersey.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import me.ahmed.projects.jersey.model.Employee2;
import me.ahmed.projects.jersey.service.OTPService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Path("app")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class AppResource {

	private static final Logger LOGGER = Logger.getLogger(AppResource.class);
	
	@Value("${otp.digit}")
	private long digit ;
	@Value("${otp.algo}")
	private String algo ;
	
	@Autowired
	private OTPService otpService ;

	@GET
	@Path("/{idUser}")
	public Employee2 test(@PathParam("idUser") String idUser) {
		LOGGER.info("test : " + idUser);
		Employee2 em = new Employee2() ;
		em.setEmployeeName(otpService.generateOTP());
		return em;
	}

}
