package com.project.banking.services.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.project.banking.dao.impl.AccountServiceDAOImpl;

public class UserValidationTest {

	private UserValidation uservalidation; 
	
	@Before
	public void setup() {
		uservalidation = new UserValidation();
	}
	
	@Test
	public void testIsValidSSN() {
		assertEquals(true, uservalidation.isValidSSN("123-12-1234"));
	}
	
	@Test
	public void testIsNotValidSSN() {
		assertEquals(false, uservalidation.isValidSSN("123121234"));
	}

	@Test
	public void testIsValidState() {
		assertEquals(true, uservalidation.isValidState("TX"));
	}
	
	@Test
	public void testIsNotValidState() {
		assertEquals(false, uservalidation.isValidState("tx"));
	}

	@Test
	public void testIsValidUsername() {
		assertEquals(true, uservalidation.isValidUsername("Username1"));
	}
	
	@Test
	public void testIsNotValidUsername() {
		assertEquals(false, uservalidation.isValidUsername("User"));
	}

	@Test
	public void testIsValidPassword() {
		assertEquals(true, uservalidation.isValidPassword("Password1"));
	}
	
	@Test
	public void testIsNotValidPassword() {
		assertEquals(false, uservalidation.isValidPassword("Pass"));
	}

	@Test
	public void testIsValidDOB() {
		assertEquals(true, uservalidation.isValidDOB("06/19/1997"));
	}
	
	@Test
	public void testIsNotValidDOB() {
		assertEquals(false, uservalidation.isValidDOB("6/19/97"));
	}

	@Test
	public void testIsValidAmount() {
		assertEquals(true, uservalidation.isValidAmount(1000f));
	}
	
	@Test
	public void testIsNotValidAmount() {
		assertEquals(false, uservalidation.isValidAmount(-1000f));
	}

}
