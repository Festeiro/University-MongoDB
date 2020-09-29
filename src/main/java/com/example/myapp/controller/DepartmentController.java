package com.example.myapp.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.dao.impl.DepartmentDAOImpl;
import com.example.myapp.model.Department;

@RestController
@RequestMapping(path="/department")
public class DepartmentController {

	DepartmentDAOImpl departmentDAO = new DepartmentDAOImpl();
	
	public DepartmentController() {
	}

	@PostMapping(path="/save")
	public boolean save(@RequestBody Department department) {
		//TODO: check if this new user already exists
		
		departmentDAO.save(department);
		return true;
	}
	
	@DeleteMapping(path="/delete")
	public boolean delete(@RequestParam Long id) {
	
		departmentDAO.delete(id);
		return false;
	}
	
	@GetMapping(path="/list")
	public ArrayList<Department> searchById(){
		return departmentDAO.listAll();
	}
}
