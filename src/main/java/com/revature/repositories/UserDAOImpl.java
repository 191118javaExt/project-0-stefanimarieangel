package com.revature.repositories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.apache.log4j.Logger;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.util.ConnectionUtil; 

public class UserDAOImpl implements UserDAO{
	
	private static Logger log = Logger.getLogger(UserDAOImpl.class);

	@Override
	public List<User> getAllUsers() {
		
		List<User> userList = new ArrayList<>();
		boolean isLoggedIn = false;
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT * FROM users;";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("user_id");
				String userFirstName = rs.getString("userFirstName");
				String userLastName = rs.getString("userLastName");
				String password = rs.getString("password");
				int account_id = rs.getInt("accountId");
				boolean isEmployee = rs.getBoolean("isEmployee");
				boolean isAdmin = rs.getBoolean("isAdmin");
			
				User u = new User(id, userFirstName, userLastName, password, account_id, isEmployee,isAdmin,isLoggedIn);
				
				userList.add(u);
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("Unable to retrieve all users", e);
		}

		
		return userList;
	}
	
	@Override
	public Set<String> getAllUsersPassword(){
		Set<String> passwords = new TreeSet<>();
		List<User> userList = getAllUsers();
		for(User u : userList) {
			String password = u.getPassword();
			passwords.add(password);
		}
		return passwords;
	}

	@Override
	public User getUserById(int id) {
			
		User user = null;
		boolean isLoggedIn = false;
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT * FROM users WHERE user_id = ? ;";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int user_id = rs.getInt("user_id");
				String userFirstName = rs.getString("userFirstName");
				String userLastName = rs.getString("userLastName");
				String password = rs.getString("password");
				int accountId = rs.getInt("accountId");
				boolean isEmployee = rs.getBoolean("isEmployee");
				boolean isAdmin = rs.getBoolean("isAdmin");
				
				user = new User(user_id, userFirstName, userLastName, password, accountId, isEmployee,isAdmin,isLoggedIn);
				
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("Unable to retrieve the user", e);
		}
		return user;
	}

	@Override
	public boolean addUser(User u) {
		try (Connection conn = ConnectionUtil.getConnection()) {
					
					
					String sql = "INSERT INTO users (userFirstName, userLastName, password, accountId, "
							+ "isEmployee, "
							+ "isAdmin) " +
							"VALUES (?, ?, ?, ?, ?, ?);";
					
					PreparedStatement stm = conn.prepareStatement(sql);
					stm.setString(1, u.getFname());
					stm.setString(2, u.getLname());
					stm.setString(3, u.getPassword());	
					stm.setInt(4, u.getAccountId());
					stm.setBoolean(5, u.isEmployee());
					stm.setBoolean(6, u.isAdmin());
					
					
					if(!stm.execute()) {
						return false;
					}
				} catch(SQLException e) {
					log.warn("Unable to add user", e);
					return false;
				}
				
				return true;
	}

	@Override
	public boolean updateUser(User u) {
		int id = u.getId();
		
		String userFirstName =  u.getFname();
		String userLastName =  u.getLname();
		String password1 =  u.getPassword();
		int account_id =  u.getAccountId();
		boolean isEmployee =  u.isEmployee();
		boolean isAdmin =  u.isAdmin();
		
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "UPDATE public.users SET userFirstName = ?, userLastName = ?, password = ?, "
					+ "account_id = ?, isEmployee = ?, isAdmin = ? "
					+" WHERE user_id = ?;"; 
					
			
			PreparedStatement stm = conn.prepareStatement(sql);
//			stm.setString(1, f_name);
//			stm.setString(2, l_name);
//			stm.setString(3, password1);
//			stm.setInt(4, accountId);
//			stm.setBoolean(5, isEmployee);
//			stm.setBoolean(6, isAdmin);
			
			stm.setString(1, userFirstName);
			stm.setString(2, userLastName);
			stm.setString(3, password1);
			stm.setInt(4, account_id);
			stm.setBoolean(5, isEmployee);
			stm.setBoolean(6, isAdmin);
			
			stm.setInt(7, id);	
			if(!stm.execute()) {
				return false;
			}
		} catch(SQLException ex) {
			log.warn("Unable to update the user", ex);
			return false;
		}
		return true;
		
	}

	@Override
	public boolean deleteUser(User u) {
		int id = u.getId();
		try (Connection conn = ConnectionUtil.getConnection()) {
						
					String sql = "DELETE FROM users"
					+ "WHERE user_id = ?;"; 
					
					PreparedStatement stm = conn.prepareStatement(sql);
					stm.setInt(1, id);
					if(!stm.execute()) {
						return false;
					}
				} catch(SQLException e) {
					log.warn("Unable to delete the user", e);
					return false;
				}		
				return true;	
	}

	@Override
	public Account getUserAccount(User u) {
		
		Account account = null;
		
		int accountId = u.getAccountId();
//		change here?
		try (Connection con = ConnectionUtil.getConnection()) {
			
		String sql = "SELECT * FROM account WHERE account_id = ? ;";
			
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, accountId);

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int account_id = rs.getInt("account_id");		
				int accountNumber = rs.getInt("accountNumber");
				double balance = rs.getDouble("balance");
				int pin = rs.getInt("pinNumber");
				account = new Account(account_id, accountNumber, balance, pin);
				
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("Unable to retrieve user's account", e);
		}	
		return account;
	}

	@Override
	public boolean updateUserAccount(User u) {
		Account a = getUserAccount(u);
		
		int id = a.getId();
		int number = a.getAccountNumber();
		double balance = a.getBalance();
		int pin = a.getPinNumber();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "UPDATE public.account SET accountNumber = ?, balance = ?, pinNumber = ? "
					+ "WHERE accountId = ?;"; 
					
			
			PreparedStatement stm = conn.prepareStatement(sql);
			
			stm.setInt(2, number);
			stm.setDouble(3, balance);
			stm.setInt(6, pin);
			stm.setInt(7, id);
			
			if(!stm.execute()) {
				return false;
			}
		} catch(SQLException e) {
			log.warn("Unable to update the user's account", e);
			return false;
		}
		return true;
		
	}

	@Override
	public User getUserByFnameAndPassword(String name, String pass) {
		User user = null;
		boolean isLoggedIn = false;
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT * FROM public.users WHERE userFirstName = ? AND password = ?;";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, name);
			stmt.setString(2, pass);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int user_id = rs.getInt("user_id");
				String userFirstName = rs.getString("userFirstName");
				String userLastName = rs.getString("userLastName");
				String password = rs.getString("password");
				int accountId = rs.getInt("accountId");
				boolean isEmployee = rs.getBoolean("isEmployee");
				boolean isAdmin = rs.getBoolean("isAdmin");
				
				user = new User(user_id, userFirstName, userLastName, password, accountId, 
						isEmployee,isAdmin,isLoggedIn);
				
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("No such user exists.", e);
		}
		return user;
	}

}

