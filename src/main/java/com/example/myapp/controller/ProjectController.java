package com.example.myapp.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.dao.impl.ProjectDAOImpl;
import com.example.myapp.model.Project;

@RestController
@RequestMapping(path="/project")
public class ProjectController {

	ProjectDAOImpl projectDAO = new ProjectDAOImpl();

	public ProjectController() {
	}

	

	@PostMapping(path="/save")
	public boolean save(@RequestBody Project project) {
		
		projectDAO.save(project);
		return true;
	}
	
	@DeleteMapping(path="/delete")
	public boolean delete(@RequestParam Long id) {
	
		projectDAO.delete(id);
		return false;
	}
	
	@GetMapping(path="/list")
	public ArrayList<Project> searchById(){
		return projectDAO.listAll();
	}

}
