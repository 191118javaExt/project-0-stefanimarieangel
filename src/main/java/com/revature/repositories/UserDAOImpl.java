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
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT * FROM users;";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int userId = rs.getInt("user_id");
				String userFirstName = rs.getString("userFirstName");
				String userLastName = rs.getString("userLastName");
				String password = rs.getString("password");
				boolean isEmployee = rs.getBoolean("isEmployee");
				boolean isAdmin = rs.getBoolean("isAdmin");
			
				User u = new User(userId, userFirstName, userLastName, password, isEmployee,isAdmin);
				
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
				boolean isEmployee = rs.getBoolean("isEmployee");
				boolean isAdmin = rs.getBoolean("isAdmin");
				
				user = new User(user_id, userFirstName, userLastName, password, isEmployee, isAdmin);
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
					stm.setInt(1, u.getUserId());
					stm.setString(2, u.getUserLastName());
					stm.setString(3, u.getPassword());	
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
		int id = u.getUserId();
		String userFirstName =  u.getUserFirstName();
		String userLastName =  u.getUserLastName();
		String password=  u.getPassword();
		boolean isEmployee =  u.isEmployee();
		boolean isAdmin =  u.isAdmin();
		
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "UPDATE public.users SET userFirstName = ?, userLastName = ?, password = ?, "
					+ " isEmployee = ?, isAdmin = ? "
					+" WHERE user_id = ?;"; 
					
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, userFirstName);
			stm.setString(2, userLastName);
			stm.setString(3, password);
			stm.setBoolean(4, isEmployee);
			stm.setBoolean(5, isAdmin);
			
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
		int id = u.getUserId();
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
		

		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT * FROM account WHERE account_id = " + u.getUserId() + " ;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			rs.next();
			int account_id = rs.getInt("account_id");
			int accountNumber = rs.getInt("accountNumber");
			double balance = rs.getDouble("balance");
			int pinNumber = rs.getInt("pinNumber");
			Account a = new Account(account_id, accountNumber, balance, pinNumber);
			
		
			rs.close();
			return a;
		} catch(SQLException e) {
			log.warn("Unable to retrieve user's account", e);
		}	
		return null;
	}

	@Override
	public boolean updateUserAccount(User u) {
		Account a = getUserAccount(u);
		
		int id = a.getId();
		int number = a.getAccountNumber();
		double balance = a.getBalance();
		int pin = a.getPinNumber();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "UPDATE public.account SET account_number = ?, balance = ?, pinNumber = ? "
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
	public User getUserByUserIdAndPassword(int userId, String password) {
		User user = null;
		//boolean isLoggedIn = false;
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT * FROM public.users WHERE user_id = ? AND password = ?;";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, userId);
			stmt.setString(2, password);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int user_id = rs.getInt("user_id");
				String userFirstName = rs.getString("userFirstName");
				String userLastName = rs.getString("userLastName");
				boolean isEmployee = rs.getBoolean("isEmployee");
				boolean isAdmin = rs.getBoolean("isAdmin");
				
				user = new User(user_id, userFirstName, userLastName, password, isEmployee,isAdmin);

				
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("No such user exists.", e);
		}
		return user;
	}

}