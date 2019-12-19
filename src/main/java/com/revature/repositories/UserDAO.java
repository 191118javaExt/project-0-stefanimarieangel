
package com.revature.repositories;

import com.revature.models.User;
import java.util.List;
import java.util.Set;

public interface UserDAO {
	
	public abstract List<User> getAllUsers();
	
	public  User getUserById(int id);
	public boolean addUser(User u);
	public boolean updateUser(User u);
	public boolean deleteUser(User u);
	
	public boolean updateUserAccount(User u);
	public User getUserByUserIdAndPassword(int userId, String password);

	Set<String> getAllUsersPassword();
	
	
	
	
}