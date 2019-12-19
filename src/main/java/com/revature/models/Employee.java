package com.revature.models;

import java.util.Objects;

public class Employee {
	private int userId;
	private String userFirstName;
	private String userLastName;
	private String password;
	private String email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, isAdmin, password, userFirstName, userId, userLastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Employee)) {
			return false;
		}
		Employee other = (Employee) obj;
		return Objects.equals(email, other.email) && isAdmin == other.isAdmin
				&& Objects.equals(password, other.password) && Objects.equals(userFirstName, other.userFirstName)
				&& userId == other.userId && Objects.equals(userLastName, other.userLastName);
	}

	@Override
	public String toString() {
		return "Employee [userId=" + userId + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
				+ ", password=" + password + ", email=" + email + ", isAdmin=" + isAdmin + "]";
	}

	public Employee(int userId, String userFirstName, String userLastName, String password, String email,
			boolean isAdmin) {
		super();
		this.userId = userId;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.password = password;
		this.email = email;
		this.isAdmin = isAdmin;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

}
