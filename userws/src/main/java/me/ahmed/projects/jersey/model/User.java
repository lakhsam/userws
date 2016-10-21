package me.ahmed.projects.jersey.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the USERR database table.
 * 
 */
@Entity
@Table(name = "Userr")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "USERR_USERID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USERR_USERID_GENERATOR")
	@Column(name="ID")
	private Long userid;

	private Date createddtm;

	private Date dateofbirth;

	private String emailaddress;

	private String firstname;

	private String lastname;

	private Date modifieddtm;

	private String phonenumber;

	// bi-directional many-to-one association to Employee
	@OneToMany(mappedBy = "user")
	private List<Employee> employees;

	// bi-directional one-to-one association to Usercredential
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Usercredential usercredential;

	// bi-directional many-to-one association to Userpasswordresettoken
	@OneToMany(mappedBy = "user")
	private List<Userpasswordresettoken> userpasswordresettokens;

	// bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name = "ADDRESSID")
	private Address address;

	// bi-directional many-to-one association to Usertype
	@ManyToOne
	@JoinColumn(name = "USERTYPECD")
	private Usertype usertype;

	public User() {
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Date getCreateddtm() {
		return this.createddtm;
	}

	public void setCreateddtm(Date createddtm) {
		this.createddtm = createddtm;
	}

	public Date getDateofbirth() {
		return this.dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getEmailaddress() {
		return this.emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getModifieddtm() {
		return this.modifieddtm;
	}

	public void setModifieddtm(Date modifieddtm) {
		this.modifieddtm = modifieddtm;
	}

	public String getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Usercredential getUsercredential() {
		return this.usercredential;
	}

	public void setUsercredential(Usercredential usercredential) {
		this.usercredential = usercredential;
	}

	public List<Userpasswordresettoken> getUserpasswordresettokens() {
		return this.userpasswordresettokens;
	}

	public void setUserpasswordresettokens(List<Userpasswordresettoken> userpasswordresettokens) {
		this.userpasswordresettokens = userpasswordresettokens;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Usertype getUsertype() {
		return this.usertype;
	}

	public void setUsertype(Usertype usertype) {
		this.usertype = usertype;
	}

}