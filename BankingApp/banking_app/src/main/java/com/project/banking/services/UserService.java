package com.project.banking.services;

import java.util.List;

import com.project.banking.exception.BusinessException;
import com.project.banking.models.Customer_Details;
import com.project.banking.models.Login_Details;

public interface UserService {

	public int getLogin(Login_Details login) throws BusinessException;
	public int newUser(Customer_Details customer) throws BusinessException;
	public List<Login_Details> getAllLogin() throws BusinessException;
	public int updateLoginStatus(String username, String status) throws BusinessException;
	public List<Customer_Details> getCID(String username) throws BusinessException;
	
}
