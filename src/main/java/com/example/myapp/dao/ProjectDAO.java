package com.example.myapp.dao;

import java.util.ArrayList;

import com.example.myapp.model.Project;

public interface ProjectDAO {

	public void save(Project project);

	public boolean delete(Long id);

	public ArrayList<Project> listAll();
	
	public Project listByProjectNumber(Long projectNumber);
}
