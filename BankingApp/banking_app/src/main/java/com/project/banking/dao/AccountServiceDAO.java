package com.project.banking.dao;

import java.util.List;

import com.project.banking.exception.BusinessException;
import com.project.banking.models.Account_Details;
import com.project.banking.models.Transaction_Details;

public interface AccountServiceDAO {
	public List<Account_Details> getAccountBalance(int a_id, int c_id) throws BusinessException; 
	public List<Account_Details> getCustomerAccounts(int c_id) throws BusinessException;
	public int conductTransaction(int a_id, String t_type, float amount) throws BusinessException;
	public int updateAccount(float balance, int a_id) throws BusinessException;
	public int createAccount(int c_id, String a_type, float balance) throws BusinessException;
	public List<Transaction_Details> getTransLog(int a_id) throws BusinessException;
	
}
