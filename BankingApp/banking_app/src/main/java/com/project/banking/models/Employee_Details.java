package com.project.banking.models;

public class Employee_Details {
	
	private int e_id;
	private String firstname;
	private String lastname;
	private String username;
	private Login_Details login_details;
	
	public Employee_Details() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee_Details(int e_id, String firstname, String lastname, String username) {
		super();
		this.e_id = e_id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
	}

	public int getE_id() {
		return e_id;
	}

	public void setE_id(int e_id) {
		this.e_id = e_id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Login_Details getLogin_details() {
		return login_details;
	}

	public void setLogin_details(Login_Details login_details) {
		this.login_details = login_details;
	}

	@Override
	public String toString() {
		return "Employee_Details [e_id=" + e_id + ", firstname=" + firstname + ", lastname=" + lastname + ", username="
				+ username + ", login_details=" + login_details + "]";
	}
	
	

}
