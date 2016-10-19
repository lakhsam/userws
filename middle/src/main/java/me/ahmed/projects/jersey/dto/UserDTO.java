package me.ahmed.projects.jersey.dto;

import me.ahmed.projects.jersey.model.User;

public class UserDTO {

	private Long userId ;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String phoneNumber;
	private int userTypeCD;
	private String address1;
	private String address2;
	private String city;
	private String codePostal;
	private String region;
	private String countryCode;
	private String driverLicenseNumber;
	private String driverLicenseExpDate;
	private String DriversLicenseIssuedRegion;
	private String sSN;
	private String EmployeePaymentAccountNumber;
	
	
	public UserDTO(){}
	public UserDTO(User user){
		this.firstname = user.getFirstname() ;
		this.lastname =user.getLastname();
		this.phoneNumber = user.getPhonenumber() ;
		this.username = user.getEmailaddress() ;
	}
	
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getUserTypeCD() {
		return userTypeCD;
	}
	public void setUserTypeCD(int userTypeCD) {
		this.userTypeCD = userTypeCD;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getDriverLicenseNumber() {
		return driverLicenseNumber;
	}
	public void setDriverLicenseNumber(String driverLicenseNumber) {
		this.driverLicenseNumber = driverLicenseNumber;
	}
	public String getDriverLicenseExpDate() {
		return driverLicenseExpDate;
	}
	public void setDriverLicenseExpDate(String driverLicenseExpDate) {
		this.driverLicenseExpDate = driverLicenseExpDate;
	}
	public String getDriversLicenseIssuedRegion() {
		return DriversLicenseIssuedRegion;
	}
	public void setDriversLicenseIssuedRegion(String driversLicenseIssuedRegion) {
		DriversLicenseIssuedRegion = driversLicenseIssuedRegion;
	}
	public String getsSN() {
		return sSN;
	}
	public void setsSN(String sSN) {
		this.sSN = sSN;
	}
	public String getEmployeePaymentAccountNumber() {
		return EmployeePaymentAccountNumber;
	}
	public void setEmployeePaymentAccountNumber(String employeePaymentAccountNumber) {
		EmployeePaymentAccountNumber = employeePaymentAccountNumber;
	}
	
	

}
