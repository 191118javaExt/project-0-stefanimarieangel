package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.util.ConnectionUtil;
import org.apache.log4j.Logger;
import com.revature.models.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {
	
	Logger logger = Logger.getLogger(EmployeeDAOImpl.class);
	
	@Override
	public List<Employee> findAll() {
		List<Employee> list = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM employee;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int userId = rs.getInt("user_Id");
				String userFirstName = rs.getString("userFirstName");
				String userLastName = rs.getString("userLastName");
				String password = rs.getString("password");
				String email =rs.getString("email");
				boolean isAdmin =rs.getBoolean("isAdmin");
				Employee e = new Employee(userId, userFirstName, userLastName, password, email, isAdmin);
				list.add(e);
			}

			rs.close();
		} catch(SQLException e) {
			logger.warn("Unable to retrieve employee", e);
		}
		return list;
	}
	
	@Override
	public Employee findById(int id) {

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM employee WHERE user_Id = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery(sql);
			
			rs.next();
			int userId = rs.getInt("user_Id");
			String userFirstName = rs.getString("userFirstName");
			String userLastName = rs.getString("userLastName");
			String password = rs.getString("password");
			String email = rs.getString("email");
			boolean isAdmin = rs.getBoolean("isAdmin");
			Employee e = new Employee(userId, userFirstName, userLastName, password, email, isAdmin);

			rs.close();
			return e;
		} catch(SQLException e) {
			logger.warn("Unable to retrieve employee", e);
		}
		return null;	
	}

	@Override
	public Employee findByEmail(String email) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM employee WHERE user_Id = '?';";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery(sql);
			
			rs.next();
			int userId = rs.getInt("user_Id");
			String userFirstName = rs.getString("userFirstName");
			String userLastName = rs.getString("userLastName");
			String password = rs.getString("password");
			String e_mail =rs.getString("email");
			boolean isAdmin =rs.getBoolean("isAdmin");
			Employee e = new Employee(userId, userFirstName, userLastName, password, e_mail, isAdmin);

			rs.close();
			return e;
		} catch(SQLException e) {
			logger.warn("Unable to retrieve employee", e);
		}
		return null;
	}

	@Override
	public boolean insert(Employee e, String password) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "INSERT INTO employee (userFirstName, userLastName, password, email, isAdmin)" +
			"VALUES(?, ?, ?, ?, ?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, e.getUserFirstName());
			stmt.setString(2, e.getUserLastName());
			stmt.setString(3, password);
			stmt.setString(4, e.getEmail());
			stmt.setBoolean(5, false);
			ResultSet rs = stmt.executeQuery(sql);
		
			
			if(!stmt.execute()) {
				return false;
			}
			rs.close();
		} catch(SQLException ex) {
			logger.warn("Unable to insert employee", ex);
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Employee e) {
// (userFirstName, userLastName, password, email, isAdmin) 
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE employee" +
					"SET userFirstName=?, userLastName=?, password=?, email=?, isAdmin=?" + 
					"WHERE user_Id = " + e.getUserId() + ";";
					PreparedStatement stmt = conn.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery(sql);
					stmt.setString(1, e.getUserFirstName());
					stmt.setString(2, e.getUserLastName());
					stmt.setString(3, e.getPassword());
					stmt.setString(4, e.getEmail());
					stmt.setBoolean(5, e.isAdmin());
					rs.close();
					if(!stmt.execute()) {
						return false;
					}
		}catch(SQLException ex) {
			logger.warn("Unable to update employee", ex);
			return false;
		}
		return true;
		
	}

}
