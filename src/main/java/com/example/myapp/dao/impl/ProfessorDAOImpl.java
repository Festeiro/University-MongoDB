package com.example.myapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bson.Document;

import com.example.myapp.dao.ProfessorDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Professor;
import com.mongodb.client.MongoCollection;

public class ProfessorDAOImpl implements ProfessorDAO{

	private MongoCollection<Professor> collection;
	
	public ProfessorDAOImpl() {
		collection = DatabaseConnection.getConnection().getCollection("Professor", Professor.class);
	}
	
	@Override
	public void save(Professor professor) {
		
		collection.insertOne(professor);		
	}

	@Override
	public void delete(Professor professor) {
		
		Document filterByGradeId = new Document("_id", professor.getId());
		collection.deleteOne(filterByGradeId);
	}

	@Override
	public ArrayList<Professor> listAll(){
		
		String sql = "SELECT * FROM Professor";
		ArrayList<Professor> professors = new ArrayList<>();
	
		return professors;
	}
	
	public Professor listByRegNumber(Long reg_number){
		
		String sql = "SELECT * FROM Professor WHERE matricula = " + reg_number;
		Professor prof = new Professor();
		
		return prof;
	}
}
