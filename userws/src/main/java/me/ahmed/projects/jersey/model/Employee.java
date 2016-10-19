package me.ahmed.projects.jersey.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the EMPLOYEE database table.
 * 
 */
@Entity
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	private String driverlicenceissuedregion;

	private String driverlicencenumber;

	private Date driverslicenceexpiration;

	private String paymentaccountnumber;

	private String ssn;

	// bi-directional many-to-one association to Userr
	@ManyToOne
	@JoinColumn(name = "USERID")
	private User user;

	public Employee() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDriverlicenceissuedregion() {
		return this.driverlicenceissuedregion;
	}

	public void setDriverlicenceissuedregion(String driverlicenceissuedregion) {
		this.driverlicenceissuedregion = driverlicenceissuedregion;
	}

	public String getDriverlicencenumber() {
		return this.driverlicencenumber;
	}

	public void setDriverlicencenumber(String driverlicencenumber) {
		this.driverlicencenumber = driverlicencenumber;
	}

	public Date getDriverslicenceexpiration() {
		return this.driverslicenceexpiration;
	}

	public void setDriverslicenceexpiration(Date driverslicenceexpiration) {
		this.driverslicenceexpiration = driverslicenceexpiration;
	}

	public String getPaymentaccountnumber() {
		return this.paymentaccountnumber;
	}

	public void setPaymentaccountnumber(String paymentaccountnumber) {
		this.paymentaccountnumber = paymentaccountnumber;
	}

	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}