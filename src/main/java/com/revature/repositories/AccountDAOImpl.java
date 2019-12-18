package com.revature.repositories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Account;
import com.revature.util.ConnectionUtil;


public class AccountDAOImpl implements AccountDAO {

	private static Logger log = Logger.getLogger(UserDAOImpl.class);
	
	@Override
	public List<Account> getAllAccounts() {
		
		List<Account> accountList = new ArrayList<>();
				
				try (Connection con = ConnectionUtil.getConnection()) {
						
					String sql = "SELECT * FROM account;";
					
					Statement stmt = con.createStatement();
					
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next()) {
						int account_id = rs.getInt("account_id");
						int accountNumber = rs.getInt("accountNumber");
						double balance = rs.getDouble("balance");
						int pin = rs.getInt("pinNumber");
						Account	account = new Account(account_id, accountNumber, balance, pin);
						accountList.add(account);
					}
					
					rs.close();
				} catch(SQLException e) {
					log.warn("Unable to retrieve all accounts", e);
				}
				
				return accountList;
	}

	@Override
	public Account getAccountById(int id) {
		Account account = null;
				
				try (Connection con = ConnectionUtil.getConnection()) {
						
					String sql = "SELECT * FROM account WHERE account_id = ? ;";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setInt(1, id);
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {
						int account_id = rs.getInt("account_id");
						int accountNumber = rs.getInt("accountNumber");
						double balance = rs.getDouble("balance");
						int pin = rs.getInt("pin");
						account = new Account(account_id, accountNumber, balance,pin);
						
					}
					
					rs.close();
				} catch(SQLException e) {
					log.warn("Unable to retrieve the account", e);
				}
				return account;
	}

	@Override
	public boolean addAccount(Account a) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "INSERT INTO account "
					+ "( accountnumber, balance, pinnumber) " +
					"VALUES (?, ?, ?);";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, a.getAccountNumber());
			stm.setDouble(2, a.getBalance());
			
			if(!stm.execute()) {
				return false;
			}
		} catch(SQLException e) {
			log.warn("Unable to add  account", e);
			return false;
		}
		
		return true;
	}

	@Override
	public boolean updateAccount(Account a) {
		
		int id = a.getId();
		int number = a.getAccountNumber();
		double balance = a.getBalance();
		int pin = a.getPinNumber();
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "UPDATE public.account SET accountNumber = ?, balance = ?, "
					+ " pinNumber = ? WHERE account_id = ?;"; 
					
			
			PreparedStatement stm = conn.prepareStatement(sql);
			
			stm.setInt(1, number);
			stm.setDouble(2, balance);
			stm.setInt(3, pin);
			stm.setInt(4, id);
			if(!stm.execute()) {
				return false;
			}
		} catch(SQLException ex) {
			log.warn("Unable to update the user's account", ex);
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteAccount(Account a) {
		int id = a.getId();
		try (Connection conn = ConnectionUtil.getConnection()) {
						
					String sql = "DELETE FROM public.account WHERE account_id = ?;"; 
					
					PreparedStatement stm = conn.prepareStatement(sql);
					stm.setInt(1, id);
					
					if(!stm.execute()) {
						return false;
					}
				} catch(SQLException e) {
					log.warn("Unable to delete the account", e);
					return false;
				}
				
				return true;
	}

	
	public Account getAccountIdBYPinNumber(int pinNumber) {
		Account account = null;
		
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT * FROM public.account WHERE pinnumber = ?;";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, pinNumber);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int account_id = rs.getInt("account_id");
				int accountNumber = rs.getInt("accountNumber");
				double balance = rs.getDouble("balance");
				int pin = rs.getInt("pinnumber");
				account = new Account(account_id, accountNumber, balance,pin);
				
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("Unable to retrieve the account by Pin Number", e);
		}
		return account;
	}

	@Override
	public boolean updateBalanceOfAccount(Account a, double amount) {
		int id = a.getId();
		int number = a.getAccountNumber();
		double balance = a.getBalance() + amount;
		int pin = a.getPinNumber();
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "UPDATE public.account SET  accountNumber = ?, balance = ?, pinNumber = ? "
					+ "WHERE account_id = ?;"; 
					
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, number);
			stm.setDouble(2, balance);
			stm.setInt(3, pin);
			stm.setInt(4, id);
			if(!stm.execute()) {
				return false;
			}
		} catch(SQLException e) {
			log.warn("Unable to update the account balance", e);
			return false;
		}
		return true;
	}

//	@Override
//	public List<Account> getAllAccounts() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Account getAccountById(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean addAccount(Account a) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean updateAccount(Account a) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean deleteAccount(Account a) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public Account getAccountIdBYPinNumber(int pinNumber) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean updateBalanceOfAccount(Account a, double amount) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}