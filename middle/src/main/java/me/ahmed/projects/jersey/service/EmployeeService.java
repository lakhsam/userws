package me.ahmed.projects.jersey.service;

import java.util.List;

import me.ahmed.projects.jersey.model.Employee2;

public interface EmployeeService {

	public Employee2 create(Employee2 employee);

	public Employee2 delete(Long id);

	public List<Employee2> findAll();

	public Employee2 update(Employee2 employee);

	public Employee2 findById(Long id);

}
