package com.revature.models;

import java.util.Objects;

public class User {
	private int userId;
	private String userFirstName;
	private String userLastName;
	private String password;	
	private boolean isEmployee;
	private boolean isAdmin;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEmployee() {
		return isEmployee;
	}
	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}



	public User(int userId, String userFirstName, String userLastName, String password, boolean isEmployee,
			boolean isAdmin) {
		super();
		this.userId = userId;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.password = password;
		this.isEmployee = isEmployee;
		this.isAdmin = isAdmin;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
				+ ", password=" + password + ", isEmployee=" + isEmployee + ", isAdmin=" + isAdmin + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(isAdmin, isEmployee, password, userFirstName, userId, userLastName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		return isAdmin == other.isAdmin && isEmployee == other.isEmployee && Objects.equals(password, other.password)
				&& Objects.equals(userFirstName, other.userFirstName) && userId == other.userId
				&& Objects.equals(userLastName, other.userLastName);
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}