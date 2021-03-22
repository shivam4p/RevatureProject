package com.project.banking.services;

import java.util.List;

import com.project.banking.exception.BusinessException;
import com.project.banking.models.Account_Details;
import com.project.banking.models.Transaction_Details;

public interface AccountService {

	public List<Account_Details> getAccountBalance(int a_id, int c_id) throws BusinessException; 
	public List<Account_Details> getCustomerAccounts(int c_id) throws BusinessException; 
	public int conductTransaction(int a_id, String t_type, int amount) throws BusinessException;
	public int updateAccount(int balance, int a_id) throws BusinessException;
	public int createAccount(int c_id, String a_type, int balance) throws BusinessException;
	public List<Transaction_Details> getTransLog(int a_id) throws BusinessException;
	
}
