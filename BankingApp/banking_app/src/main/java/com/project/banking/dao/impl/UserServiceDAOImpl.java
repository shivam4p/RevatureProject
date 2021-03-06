package com.project.banking.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.project.banking.dao.UserServiceDAO;
import com.project.banking.exception.BusinessException;
import com.project.banking.main.BankingMain;
import com.project.banking.models.Customer_Details;
import com.project.banking.models.Login_Details;

import jdk.internal.org.jline.utils.Log;

import com.project.banking.dbutil.PostgresConnection;

public class UserServiceDAOImpl implements UserServiceDAO{
	private static Logger log=Logger.getLogger(UserServiceDAOImpl.class);
	
	@Override
	public int getLogin(Login_Details login) throws BusinessException {
		int c=0;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "insert into banking_app_schema.login_details(username, password) values(?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, login.getUsername());
			preparedStatement.setString(2, login.getPassword());
			c=preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BusinessException("Username Already Taken");
		}
		
		return c;
	}

	@Override
	public int newUser(Customer_Details customer) throws BusinessException {
		int c=0;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "insert into banking_app_schema.customer_details(firstname,lastname,ssn,streetaddress,city,state,username,dob) values(?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getFirstname());
			preparedStatement.setString(2, customer.getLastname());
			preparedStatement.setString(3, customer.getSsn());
			preparedStatement.setString(4, customer.getStreetaddress());
			preparedStatement.setString(5, customer.getCity());
			preparedStatement.setString(6, customer.getState());
			preparedStatement.setString(7, customer.getUsername());
			preparedStatement.setString(8, customer.getDob());
		//	preparedStatement.setString(9, customer.getPassword());
			c=preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BusinessException("Entered User Name Already Has Account, Or Account Hasn't Been Created");
		}
		
		return c;
	}

	@Override
	public List<Login_Details> getAllLogin() throws BusinessException {
		List<Login_Details> loginList=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select username, password, status from banking_app_schema.login_details";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			
			
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Login_Details logins = new Login_Details();
				logins.setUsername(resultSet.getString("username"));
				logins.setPassword(resultSet.getString("password"));
				logins.setStatus(resultSet.getString("status"));
				loginList.add(logins);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BusinessException("Internal Error");
		}
		return loginList;
	}

	@Override
	public int updateLoginStatus(String username, String status) throws BusinessException {
		int c=0;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "update banking_app_schema.login_details set status=? where username=?" ;
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, status);
			preparedStatement.setString(2, username);
			c=preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BusinessException("Internal Error");
		}
		
		return c;
	}

	@Override
	public List<Customer_Details> getCID(String username) throws BusinessException {
		List<Customer_Details> customerList=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select c_id from banking_app_schema.customer_details where username=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Customer_Details customers = new Customer_Details();
				customers.setUsername(username);
				customers.setC_id(resultSet.getInt("c_id"));
				customerList.add(customers);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BusinessException("Internal Error");
		}
		return customerList;
	}

	

}
