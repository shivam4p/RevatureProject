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
	public int newUser(Customer_Details User) throws BusinessException {
		if(!UserValidation.isValidSSN(User.getSsn())) {
			throw new BusinessException("Entered SSN "+User.getSsn()+" is invalid");
		}
		if(!UserValidation.isValidDOB(User.getDob())) {
			throw new BusinessException("Entered Date of Birth "+User.getDob()+" is invalid");
		}
		if(!UserValidation.isValidState(User.getState())) {
			throw new BusinessException("Entered State Initials "+User.getState()+" is invalid");
		}
		if(!UserValidation.isValidUsername(User.getUsername())) {
			throw new BusinessException("Entered Username "+User.getUsername()+" is invalid");
		}
		if(!UserValidation.isValidPassword(User.getPassword())) {
			throw new BusinessException("Entered Password "+User.getPassword()+" is invalid");
		}
		return userservicedao.newUser(User);
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
