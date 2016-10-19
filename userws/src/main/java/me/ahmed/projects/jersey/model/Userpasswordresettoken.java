package me.ahmed.projects.jersey.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the USERPASSWORDRESETTOKEN database table.
 * 
 */
@Entity
@NamedQuery(name="Userpasswordresettoken.findAll", query="SELECT u FROM Userpasswordresettoken u")
public class Userpasswordresettoken implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USERPASSWORDRESETTOKEN_USERPASSWORDRESETTOKENID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.AUTO, generator="USERPASSWORDRESETTOKEN_USERPASSWORDRESETTOKENID_GENERATOR")
	private long userpasswordresettokenid;

	private Timestamp createddtm;

	private Timestamp expirationdtm;

	private BigDecimal temporarypassworddigest;

	//bi-directional many-to-one association to Userr
	@ManyToOne
	@JoinColumn(name="USERID")
	private User user;

	public Userpasswordresettoken() {
	}

	public long getUserpasswordresettokenid() {
		return this.userpasswordresettokenid;
	}

	public void setUserpasswordresettokenid(long userpasswordresettokenid) {
		this.userpasswordresettokenid = userpasswordresettokenid;
	}

	public Timestamp getCreateddtm() {
		return this.createddtm;
	}

	public void setCreateddtm(Timestamp createddtm) {
		this.createddtm = createddtm;
	}

	public Timestamp getExpirationdtm() {
		return this.expirationdtm;
	}

	public void setExpirationdtm(Timestamp expirationdtm) {
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