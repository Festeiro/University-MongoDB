package com.example.myapp.dao;

import java.util.ArrayList;

import org.bson.types.ObjectId;

import com.example.myapp.model.Professor;

public interface ProfessorDAO {

	public void save(Professor professor);

	public void delete(ObjectId id);

	public ArrayList<Professor> listAll();

}
