package com.revature.repositories;
import java.util.List;
import com.revature.models.Admin;

public interface AdminDAO {
	public List<Admin> findAll();
	public Admin findById(int id);
	public Admin findByUsername(String username);
	public boolean insert(Admin a, String password);
	public boolean update(Admin a);
}
