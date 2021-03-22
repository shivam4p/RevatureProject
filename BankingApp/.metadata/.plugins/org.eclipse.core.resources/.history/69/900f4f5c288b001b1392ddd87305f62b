package com.project.banking.services.impl;

import java.util.List;

import com.project.banking.dao.AccountServiceDAO;
import com.project.banking.dao.UserServiceDAO;
import com.project.banking.dao.impl.AccountServiceDAOImpl;
import com.project.banking.dao.impl.UserServiceDAOImpl;
import com.project.banking.exception.BusinessException;
import com.project.banking.models.Account_Details;
import com.project.banking.models.Transaction_Details;
import com.project.banking.services.AccountService;

public class AccountServiceImpl implements AccountService {
	private AccountServiceDAO accountservicedao = new AccountServiceDAOImpl();
	
	@Override
	public List<Account_Details> getAccountBalance(int a_id, int c_id) throws BusinessException {
		return accountservicedao.getAccountBalance(a_id, c_id);
	}

	@Override
	public List<Account_Details> getCustomerAccounts(int c_id) throws BusinessException {
		return accountservicedao.getCustomerAccounts(c_id);
	}

	@Override
	public int conductTransaction(int a_id, String t_type, int amount) throws BusinessException {
		if(!UserValidation.isValidAmount(amount)) {
			throw new BusinessException("Entered Amount "+amount+" is invalid");
		}
		return accountservicedao.conductTransaction(a_id,t_type,amount);
	}

	@Override
	public int updateAccount(int balance, int a_id) throws BusinessException {
		return accountservicedao.updateAccount(balance, a_id);
	}

	@Override
	public int createAccount(int c_id, String a_type, int balance) throws BusinessException {
		return accountservicedao.createAccount(c_id, a_type, balance);
	}

	@Override
	public List<Transaction_Details> getTransLog(int a_id) throws BusinessException {
		return accountservicedao.getTransLog(a_id);
	}

	
}
