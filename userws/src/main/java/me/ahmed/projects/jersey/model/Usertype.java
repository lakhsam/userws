package me.ahmed.projects.jersey.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the USERTYPE database table.
 * 
 */
@Entity
@NamedQuery(name = "Usertype.findAll", query = "SELECT u FROM Usertype u")
public class Usertype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "USERTYPE_USERTYPECD_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USERTYPE_USERTYPECD_GENERATOR")
	private long usertypecd;

	private String description;

	private String name;

	// bi-directional many-to-one association to Userr
	@OneToMany(mappedBy = "usertype")
	private List<User> users;

	public Usertype() {
	}

	public long getUsertypecd() {
		return this.usertypecd;
	}

	public void setUsertypecd(long usertypecd) {
		this.usertypecd = usertypecd;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}