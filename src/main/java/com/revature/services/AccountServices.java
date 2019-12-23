package com.revature.services;
import java.util.List;
import com.revature.models.Account;

import com.revature.repositories.AccountDAO;
import com.revature.repositories.AccountDAOImpl;

public class AccountServices {
	
	AccountDAO accountDAO = new AccountDAOImpl(); 
	
	public  List<Account> getAllAccounts(){
		
		return accountDAO.getAllAccounts();
	}
	
	
	
	public  Account getAccountById(int id) {
		return accountDAO.getAccountById(id);
	}
	
	public  boolean addAccount(Account a) {
		return accountDAO.addAccount(a);
	}
	
	public  boolean updateAccount(Account a) {
		return accountDAO.updateAccount(a);
	}
	
	public  boolean deleteAccount(Account a) {
		return accountDAO.deleteAccount(a);
	}
	
	public  Account getAccountBYPinNumber(int pin) {
		return accountDAO.getAccountIdBYPinNumber(pin);
	}
	
	public  boolean updateBalanceOfAccount(Account a, double amount) {
		return accountDAO.updateBalanceOfAccount(a, amount);
	}
}