package me.ahmed.projects.jersey.repository;

import me.ahmed.projects.jersey.model.Employee2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeRepository extends JpaRepository<Employee2, Long>,
		JpaSpecificationExecutor<Employee2> {

}