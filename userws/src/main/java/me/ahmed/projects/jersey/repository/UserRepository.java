package me.ahmed.projects.jersey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.ahmed.projects.jersey.model.User;

public interface UserRepository extends JpaRepository<User, Long>,
		JpaSpecificationExecutor<User> {
	
	public User findByEmailaddress(String username) ;

}