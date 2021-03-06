package com.project.banking.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.project.banking.dao.AccountServiceDAO;
import com.project.banking.dbutil.PostgresConnection;
import com.project.banking.exception.BusinessException;
import com.project.banking.models.Account_Details;
import com.project.banking.models.Transaction_Details;

import jdk.internal.org.jline.utils.Log;

public class AccountServiceDAOImpl implements AccountServiceDAO {
	private static Logger log=Logger.getLogger(AccountServiceDAOImpl.class);
	
	@Override
	public List<Account_Details> getAccountBalance(int a_id, int c_id) throws BusinessException {
		List<Account_Details> accountList=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select balance from banking_app_schema.account_details where (a_id=?) and (c_id=?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, a_id);
			preparedStatement.setInt(2, c_id);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Account_Details account=new Account_Details();
				account.setA_id(a_id);
				account.setC_id(c_id);
				account.setBalance(resultSet.getFloat("balance"));
				accountList.add(account);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BusinessException("Internal Error");
		}
		return accountList;
	}

	@Override
	public List<Account_Details> getCustomerAccounts(int c_id) throws BusinessException {
		List<Account_Details> accountList=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select a_id, a_type, balance from banking_app_schema.account_details where c_id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_id);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Account_Details account=new Account_Details();
				account.setA_id(resultSet.getInt("a_id"));
				account.setC_id(c_id);
				account.setA_type(resultSet.getString("a_type"));
				account.setBalance(resultSet.getFloat("balance"));
				accountList.add(account);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BusinessException("Internal Error");
		}
		return accountList;
	}

	@Override
	public int conductTransaction(int a_id, String t_type, float amount) throws BusinessException {
		int c=0;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "insert into banking_app_schema.transaction_details(a_id,t_type,amount) values (?,?,?)" ;
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, a_id);
			preparedStatement.setString(2, t_type);
			preparedStatement.setFloat(3, amount);
			c=preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BusinessException("Internal Error");
		}
		
		return c;
	}

	@Override
	public int updateAccount(float balance, int a_id) throws BusinessException {
		int c=0;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "update banking_app_schema.account_details set balance=? where a_id=?" ;
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setFloat(1, balance);
			preparedStatement.setInt(2, a_id);
			c=preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BusinessException("Internal Error");
		}
		
		return c;
	}

	@Override
	public int createAccount(int c_id, String a_type, float balance) throws BusinessException {
		int c=0;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "insert into banking_app_schema.account_details(c_id,a_type,balance) values(?,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_id);
			preparedStatement.setString(2, a_type);
			preparedStatement.setFloat(3, balance);
			c=preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BusinessException("Internal Error");
		}
		
		return c;
	}

	@Override
	public List<Transaction_Details> getTransLog(int a_id) throws BusinessException {
		List<Transaction_Details> transactionList=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select t_type, amount, date from banking_app_schema.transaction_details where a_id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, a_id);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Transaction_Details transaction=new Transaction_Details();
				transaction.setA_id(a_id);
				transaction.setT_type(resultSet.getString("t_type"));
				transaction.setAmount(resultSet.getFloat("amount"));
				transaction.setDate(resultSet.getString("date"));
				transactionList.add(transaction);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BusinessException("Internal Error");
		}
		return transactionList;
	}


}
