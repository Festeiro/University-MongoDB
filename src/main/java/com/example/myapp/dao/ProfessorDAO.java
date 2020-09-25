package com.example.myapp.dao;

import java.util.ArrayList;

import com.example.myapp.model.Professor;

public interface ProfessorDAO {

	public void save(Professor professor);

	public void delete(Professor professor);

	public ArrayList<Professor> listAll();

}
