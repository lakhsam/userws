package me.ahmed.projects.jersey.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * The persistent class for the ADDRESS database table.
 * 
 */
@Entity
@NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ADDRESS_ADDRESSID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ADDRESS_ADDRESSID_GENERATOR")
	private Long addressid;

	private BigDecimal addressdigest;

	private String addressline1;

	private String addressline2;

	private String city;

	private String country;

	private String region;

	// bi-directional many-to-one association to Userr
	@OneToMany(mappedBy = "address")
	private List<User> users;

	public Address() {
	}

	public Long getAddressid() {
		return this.addressid;
	}

	public void setAddressid(Long addressid) {
		this.addressid = addressid;
	}

	public BigDecimal getAddressdigest() {
		return this.addressdigest;
	}

	public void setAddressdigest(BigDecimal addressdigest) {
		this.addressdigest = addressdigest;
	}

	public String getAddressline1() {
		return this.addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return this.addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}