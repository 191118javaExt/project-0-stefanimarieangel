package com.revature.services;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.EmployeeDAOImpl;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserDAOImpl;

public class EmployeeServices {
	
	EmployeeDAO repository = null;
	UserDAO u_repository = new UserDAOImpl();
	Scanner scan = new Scanner(System.in);

	public EmployeeServices() {
		repository = new EmployeeDAOImpl();
	}

	public EmployeeServices(EmployeeDAO dao) {
		repository = dao;
	}

	public List<User> findAllUsers(){
		return u_repository.getAllUsers();
	}

	public boolean insert(User u, String password) {
		u.setPassword(password);
		return u_repository.addUser(u);
	}

	public boolean login() {
		String email;
		String password;
		Employee user;
		
		System.out.println("Enter your email: ");
		email = scan.nextLine();
		
		user = repository.findByEmail(email);
		System.out.println("Enter your password: ");
		password = scan.nextLine();
		
		if(email.equals(user.getEmail()) && password.equals(user.getPassword())) {
			System.out.println("Welcome " + user.getUserFirstName() + " " + user.getUserLastName() + ".");
			return true;
		}
	
		System.out.println("Try again.");
		return false;
	}
}
