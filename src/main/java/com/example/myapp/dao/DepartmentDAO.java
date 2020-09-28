package com.example.myapp.dao;

import java.util.ArrayList;

import com.example.myapp.model.Department;

public interface DepartmentDAO {

	public void save(Department department);
	
	public boolean delete(String id);
		
	public ArrayList<Department> listAll();	
}
