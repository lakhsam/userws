package me.ahmed.projects.jersey.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.ahmed.projects.jersey.model.Employee2;
import me.ahmed.projects.jersey.repository.EmployeeRepository;
import me.ahmed.projects.jersey.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Resource
	EmployeeRepository employeeRepository;

	@Override
	public Employee2 create(Employee2 employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee2 delete(Long id) {
		Employee2 employee = employeeRepository.findOne(id);
		if (employee == null)
			return null;
		employeeRepository.delete(employee);
		return employee;

	}

	@Override
	public List<Employee2> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee2 update(Employee2 employee) {
		Employee2 updatedEmployee = employeeRepository.findOne(employee
				.getEmployeeId());
		if (updatedEmployee == null)
			return null;
		updatedEmployee.setEmployeeName(employee.getEmployeeName());
		return updatedEmployee;

	}

	@Override
	public Employee2 findById(Long id) {
		return employeeRepository.findOne(id);
	}

}
