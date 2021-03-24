package com.project.banking.models;

public class Transaction_Details {

	private int t_id;
	private int a_id;
	private String t_type;
	private float amount;
	private String date;
	private Account_Details account_details;
	
	public Transaction_Details() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction_Details(int t_id, int a_id, String t_type, float amount, String date) {
		super();
		this.t_id = t_id;
		this.a_id = a_id;
		this.t_type = t_type;
		this.amount = amount;
		this.date = date;
	}

	public int getT_id() {
		return t_id;
	}

	public void setT_id(int t_id) {
		this.t_id = t_id;
	}

	public int getA_id() {
		return a_id;
	}

	public void setA_id(int a_id) {
		this.a_id = a_id;
	}

	public String getT_type() {
		return t_type;
	}

	public void setT_type(String t_type) {
		this.t_type = t_type;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Account_Details getAccount_details() {
		return account_details;
	}

	public void setAccount_details(Account_Details account_details) {
		this.account_details = account_details;
	}

	@Override
	public String toString() {
		return "Transaction_Details [t_id=" + t_id + ", a_id=" + a_id + ", t_type=" + t_type + ", amount=" + amount
				+ ", date=" + date + ", account_details=" + account_details + "]";
	}
	
	
	
}
