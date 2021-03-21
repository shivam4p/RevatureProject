package com.project.banking.models;

public class Login_Details {

	private String username;
	private String password;
	private String status;
	
	public Login_Details() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Login_Details(String username, String password, String status) {
		super();
		this.username = username;
		this.password = password;
		this.status = status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Login_Details [username=" + username + ", password=" + password + ", status=" + status + "]";
	}
	
	
}
