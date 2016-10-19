package me.ahmed.projects.jersey.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import me.ahmed.projects.jersey.model.Employee2;
import me.ahmed.projects.jersey.service.EmployeeService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path("employee")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class EmployeeResource {

	private static final Logger LOGGER = Logger
			.getLogger(EmployeeResource.class);

	@Autowired
	EmployeeService employeeService;

	@GET
	@Path("/{id}")
	public Object getById(@PathParam("id") Long id) {
		LOGGER.info("test : " + id);
		Employee2 employee = employeeService.findById(id);
		if (employee != null) {
			return employee;
		}
		return "Employee with id : " + id + " Not found ";
	}

	@POST
	@Path("save/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Object save(Employee2 employee) {
		LOGGER.info("test : " + employee.getEmployeeId());
		Employee2 savedEmployee = employeeService.create(employee);
		if (savedEmployee != null) {
			return savedEmployee;
		}
		return "ERROR SAVE";
	}
	
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Object getALL() {
		return employeeService.findAll();
	}

}
