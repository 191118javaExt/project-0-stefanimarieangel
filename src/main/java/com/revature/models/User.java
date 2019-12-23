package com.revature.models;

public class User {
	private int id;
	private String fname;
	private String lname;
	private String password;	
	private int accountId;
	private boolean isEmployee;
	private boolean isAdmin;
	private boolean isLoggedIn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
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

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public User(int id, String fname, String lname, String password, int accountId, boolean employee, boolean admin,
			boolean isLoggedIn) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.accountId = accountId;
		this.isEmployee = employee;
		this.isAdmin = admin;
		this.isLoggedIn = isLoggedIn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + (isAdmin ? 1231 : 1237);
		result = prime * result + (isEmployee ? 1231 : 1237);
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + id;
		result = prime * result + (isLoggedIn ? 1231 : 1237);
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
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
		if (accountId != other.accountId)
			return false;
		if (isAdmin != other.isAdmin)
			return false;
		if (isEmployee != other.isEmployee)
			return false;
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (id != other.id)
			return false;
		if (isLoggedIn != other.isLoggedIn)
			return false;
		if (lname == null) {
			if (other.lname != null)
				return false;
		} else if (!lname.equals(other.lname))
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
		return "User [id=" + id + ", fname=" + fname + ", lname=" + lname + ", password=" + password + ", accountId="
				+ accountId + ", employee=" + isEmployee + ", admin=" + isAdmin + ", isLoggedIn=" + isLoggedIn + "]";
	}

	
	
	

}