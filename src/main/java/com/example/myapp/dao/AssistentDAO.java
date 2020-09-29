package com.example.myapp.dao;

import java.util.ArrayList;

import com.example.myapp.model.Assistent;

public interface AssistentDAO {

	public void save(Assistent assistent);
	
	public boolean delete(Long studentRegNumber);
	
	public ArrayList<Assistent> listByStudentRegNumber(Long studentRegNumber);
}
