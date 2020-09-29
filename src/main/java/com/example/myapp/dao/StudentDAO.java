package com.example.myapp.dao;

import java.util.ArrayList;

import com.example.myapp.model.Student;

public interface StudentDAO {

	public void save(Student student);
	
	public boolean delete(Long id);
	
	public ArrayList<Student> listAll();
	
}
