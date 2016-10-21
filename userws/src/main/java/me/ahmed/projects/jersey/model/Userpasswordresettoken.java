package me.ahmed.projects.jersey.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * The persistent class for the USERPASSWORDRESETTOKEN database table.
 * 
 */
@Entity
@NamedQuery(name = "Userpasswordresettoken.findAll", query = "SELECT u FROM Userpasswordresettoken u")
public class Userpasswordresettoken implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "USERPASSWORDRESETTOKEN_USERPASSWORDRESETTOKENID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USERPASSWORDRESETTOKEN_USERPASSWORDRESETTOKENID_GENERATOR")
	private long userpasswordresettokenid;

	private Date createddtm;

	private Date expirationdtm;

	private BigDecimal temporarypassworddigest;

	// bi-directional many-to-one association to Userr
	@ManyToOne
	@JoinColumn(name = "USERID")
	private User user;

	public Userpasswordresettoken() {
	}

	public long getUserpasswordresettokenid() {
		return this.userpasswordresettokenid;
	}

	public void setUserpasswordresettokenid(long userpasswordresettokenid) {
		this.userpasswordresettokenid = userpasswordresettokenid;
	}

	public Date getCreateddtm() {
		return this.createddtm;
	}

	public void setCreateddtm(Date createddtm) {
		this.createddtm = createddtm;
	}

	public Date getExpirationdtm() {
		return this.expirationdtm;
	}

	public void setExpirationdtm(Date expirationdtm) {
		this.expirationdtm = expirationdtm;
	}

	public BigDecimal getTemporarypassworddigest() {
		return this.temporarypassworddigest;
	}

	public void setTemporarypassworddigest(BigDecimal temporarypassworddigest) {
		this.temporarypassworddigest = temporarypassworddigest;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}