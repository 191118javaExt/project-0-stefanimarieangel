package com.revature.repositories;
import java.util.List;
import com.revature.models.Account;


public interface AccountDAO {

	public abstract List<Account> getAllAccounts();
	
	public abstract Account getAccountById(int id);
	
	public abstract boolean addAccount(Account a);
	
	public abstract boolean updateAccount(Account a);
	
	public abstract boolean deleteAccount(Account a);
	
	public abstract Account getAccountIdBYPinNumber(int pinNumber);
	
	public abstract boolean updateBalanceOfAccount(Account a, double amount);
	
}