package com.project.banking.dao.impl;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.project.banking.dao.AccountServiceDAO;
import com.project.banking.exception.BusinessException;
import com.project.banking.models.Account_Details;
import com.project.banking.models.Transaction_Details;

public class AccountServiceDAOImplTest {

	private AccountServiceDAO accountservicedao;
	
	@Before
	public void setup() {
		accountservicedao = new AccountServiceDAOImpl();
	}
	
	
	@Test
	public void testConductTransaction() {
		try {
			assertEquals(1, accountservicedao.conductTransaction(1, "Deposit", 500));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateAccount() {
		try {
			assertEquals(1, accountservicedao.updateAccount(2500, 2));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateAccount() {
		try {
			assertEquals(1, accountservicedao.createAccount(5, "Savings", 1000));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
