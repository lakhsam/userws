package me.ahmed.batch.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import me.ahmed.batch.tools.AcceptedValues;

import org.springframework.format.annotation.DateTimeFormat;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	@Size(min = 3, max = 10, message = "Invalid username value")
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String gsm;
	private String statut;
	@NotNull(message = "Date is required")  
    @Past(message = "Order date should be in the past")  
    @DateTimeFormat(pattern = "dd-MM-yyyy")  
	private Date creationDate;
	@Min(value = 0, message = "Version less than 1")
	@Max(value = 802, message = "Version more than 9")
	private int version;
	@AcceptedValues(acceptValues = { "23890" }, message = "Invalid service type")
	private String codeBanque;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGsm() {
		return gsm;
	}

	public void setGsm(String gsm) {
		this.gsm = gsm;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCodeBanque() {
		return codeBanque;
	}

	public void setCodeBanque(String codeBanque) {
		this.codeBanque = codeBanque;
	}

}
