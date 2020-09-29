package com.example.myapp.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.dao.impl.StudentDAOImpl;
import com.example.myapp.model.Student;

@RestController
@RequestMapping(path="/student")
public class StudentController {

	StudentDAOImpl studentDAO = new StudentDAOImpl();
	
	public StudentController() {
	}

	@PostMapping(path="/save")
	public boolean save(@RequestBody Student student) {
		
		studentDAO.save(student);
		return true;
	}
	
	@DeleteMapping(path="/delete")
	public boolean delete(@RequestParam Long id) {
	
		return studentDAO.delete(id);
	}
	
	@GetMapping(path="/list")
	public ArrayList<Student> searchById(){
		return studentDAO.listAll();
	}
}
