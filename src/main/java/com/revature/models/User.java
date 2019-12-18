package com.revature.models;

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

	public String getuserFirstName() {
		return userFirstName;
	}

	public void setuserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getuserLastName() {
		return userLastName;
	}

	public void setuserLastName(String userLastName) {
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

	public void setEmployee(boolean employee) {
		this.isEmployee = employee;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean admin) {
		this.isAdmin = admin;
	}

	public User(int userId, String userFirstName, String userLastName, String password, boolean employee, boolean admin) {
		super();
		this.userId = userId;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.password = password;
		this.isEmployee = employee;
		this.isAdmin = admin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isAdmin ? 1231 : 1237);
		result = prime * result + (isEmployee ? 1231 : 1237);
		result = prime * result + ((userFirstName == null) ? 0 : userFirstName.hashCode());
		result = prime * result + userId;
		result = prime * result + ((userLastName == null) ? 0 : userLastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (isAdmin != other.isAdmin)
			return false;
		if (isEmployee != other.isEmployee)
			return false;
		if (userFirstName == null) {
			if (other.userFirstName != null)
				return false;
		} else if (!userFirstName.equals(other.userFirstName))
			return false;
		if (userId != other.userId)
			return false;
		if (userLastName == null) {
			if (other.userLastName != null)
				return false;
		} else if (!userLastName.equals(other.userLastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName + ", password=" + password 
				 + ", isEmployee=" + isEmployee + ", admin=" + isAdmin + "]";
	}
}