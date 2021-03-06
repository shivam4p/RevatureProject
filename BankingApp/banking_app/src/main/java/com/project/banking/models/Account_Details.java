package com.project.banking.models;

public class Account_Details {

	private int a_id;
	private int c_id;
	private String a_type;
	private float balance;
	private Customer_Details customer_details;
	
	public Account_Details() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account_Details(int a_id, int c_id, String a_type, float balance) {
		super();
		this.a_id = a_id;
		this.c_id = c_id;
		this.a_type = a_type;
		this.balance = balance;
	}

	public int getA_id() {
		return a_id;
	}

	public void setA_id(int a_id) {
		this.a_id = a_id;
	}

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public String getA_type() {
		return a_type;
	}

	public void setA_type(String a_type) {
		this.a_type = a_type;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public Customer_Details getCustomer_details() {
		return customer_details;
	}

	public void setCustomer_details(Customer_Details customer_details) {
		this.customer_details = customer_details;
	}

	@Override
	public String toString() {
		return "Account_Details [a_id=" + a_id + ", c_id=" + c_id + ", a_type=" + a_type + ", balance=" + balance
				+ ", customer_details=" + customer_details + "]";
	}

	
	
}
