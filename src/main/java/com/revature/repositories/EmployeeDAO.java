package com.revature.repositories;
import java.util.List;
import com.revature.models.Employee;

public interface EmployeeDAO {
	public List<Employee> findAll();
	public Employee findById(int id);
	public Employee findByEmail(String email);
	public boolean insert(Employee e, String password);
	public boolean update(Employee e);
}
