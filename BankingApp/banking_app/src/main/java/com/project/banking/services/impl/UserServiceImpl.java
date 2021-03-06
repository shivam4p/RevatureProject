package com.project.banking.services.impl;

import java.util.List;

import com.project.banking.dao.UserServiceDAO;
import com.project.banking.dao.impl.UserServiceDAOImpl;
import com.project.banking.exception.BusinessException;
import com.project.banking.models.Customer_Details;
import com.project.banking.models.Login_Details;
import com.project.banking.services.UserService;

public class UserServiceImpl implements UserService {
	private UserServiceDAO userservicedao = new UserServiceDAOImpl();
	
	@Override
	public int getLogin(Login_Details login) throws BusinessException {
		if(!UserValidation.isValidUsername(login.getUsername())) {
			throw new BusinessException("Entered Username "+login.getUsername()+" is invalid");
		}
		if(!UserValidation.isValidPassword(login.getPassword())) {
			throw new BusinessException("Entered Password "+login.getPassword()+" is invalid");
		}
		return userservicedao.getLogin(login);
	}

	@Override
	public int newUser(Customer_Details customer) throws BusinessException {
		if(!UserValidation.isValidSSN(customer.getSsn())) {
			throw new BusinessException("Entered SSN "+customer.getSsn()+" is invalid");
		}
		if(!UserValidation.isValidDOB(customer.getDob())) {
			throw new BusinessException("Entered Date of Birth "+customer.getDob()+" is invalid");
		}
		if(!UserValidation.isValidState(customer.getState())) {
			throw new BusinessException("Entered State Initials "+customer.getState()+" is invalid");
		}
		if(!UserValidation.isValidUsername(customer.getUsername())) {
			throw new BusinessException("Entered Username "+customer.getUsername()+" is invalid");
		}
		return userservicedao.newUser(customer);
	}

	@Override
	public List<Login_Details> getAllLogin() throws BusinessException {
		return userservicedao.getAllLogin();
	}

	@Override
	public int updateLoginStatus(String username, String status) throws BusinessException {
		return userservicedao.updateLoginStatus(username,status);
	}

	@Override
	public List<Customer_Details> getCID(String username) throws BusinessException {
		return userservicedao.getCID(username);
	}	
	

}
