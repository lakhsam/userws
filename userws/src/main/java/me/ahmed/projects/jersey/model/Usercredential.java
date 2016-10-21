package me.ahmed.projects.jersey.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the USERCREDENTIALS database table.
 * 
 */
@Entity
@Table(name = "USERCREDENTIALS")
@NamedQuery(name = "Usercredential.findAll", query = "SELECT u FROM Usercredential u")
public class Usercredential implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "USERCREDENTIALS_USERID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USERCREDENTIALS_USERID_GENERATOR")
	@Column(name="ID")
	private Long userid;

	private int consecutivefailedloginattempts;

	private Date createddtm;

	private Date modifieddtm;

	private int passworddigest;

	// bi-directional one-to-one association to Userr
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USERID")
	private User user;

	public Usercredential() {
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public int getConsecutivefailedloginattempts() {
		return this.consecutivefailedloginattempts;
	}

	public void setConsecutivefailedloginattempts(int consecutivefailedloginattempts) {
		this.consecutivefailedloginattempts = consecutivefailedloginattempts;
	}

	public Date getCreateddtm() {
		return this.createddtm;
	}

	public void setCreateddtm(Date createddtm) {
		this.createddtm = createddtm;
	}

	public Date getModifieddtm() {
		return this.modifieddtm;
	}

	public void setModifieddtm(Date modifieddtm) {
		this.modifieddtm = modifieddtm;
	}

	public int getPassworddigest() {
		return this.passworddigest;
	}

	public void setPassworddigest(int passworddigest) {
		this.passworddigest = passworddigest;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}