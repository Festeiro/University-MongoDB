package com.example.myapp.dao;

import java.util.ArrayList;

import com.example.myapp.model.Department;

public interface DepartmentDAO {

	public void save(Department department);
	
	public boolean delete(Long id);
		
	public ArrayList<Department> listAll();	
}
