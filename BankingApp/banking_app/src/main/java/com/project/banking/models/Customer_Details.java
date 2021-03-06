package com.project.banking.models;

public class Customer_Details {

	private int c_id;
	private String firstname;
	private String lastname;
	private String ssn;
	private String streetaddress;
	private String city;
	private String state;
	private String username;
	private String dob;
	private Login_Details login_details;
	
	public Customer_Details() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Customer_Details(int c_id, String firstname, String lastname, String ssn, String streetaddress, String city,
			String state, String username, String dob) {
		super();
		this.c_id = c_id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.ssn = ssn;
		this.streetaddress = streetaddress;
		this.city = city;
		this.state = state;
		this.username = username;
		this.dob = dob;
	}

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
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

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getStreetaddress() {
		return streetaddress;
	}

	public void setStreetaddress(String streetaddress) {
		this.streetaddress = streetaddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Login_Details getLogin_details() {
		return login_details;
	}

	public void setLogin_details(Login_Details login_details) {
		this.login_details = login_details;
	}

	@Override
	public String toString() {
		return "Customer_Details [c_id=" + c_id + ", firstname=" + firstname + ", lastname=" + lastname + ", ssn=" + ssn
				+ ", streetaddress=" + streetaddress + ", city=" + city + ", state=" + state + ", username=" + username
				+ ", dob=" + dob + ", login_details=" + login_details + "]";
	}

	
	
	
	
	
	
	

}
