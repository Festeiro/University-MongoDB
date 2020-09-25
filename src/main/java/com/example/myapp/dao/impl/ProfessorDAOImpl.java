package com.example.myapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.myapp.dao.ProfessorDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Professor;

public class ProfessorDAOImpl implements ProfessorDAO{

	public ProfessorDAOImpl() {
	}
	
	@Override
	public void save(Professor professor) {
		
		String sql = "";
		if(professor.getReg_number() == null) {
			sql = "INSERT INTO professor(Nome, Idade, Sala, Especialidade) VALUES('" + professor.getName() + "', " + 
					professor.getAge() + ", '" + professor.getClassRoom() + "', '" + professor.getSpeciality() + "')";
		}
		else {
			sql = "UPDATE professor"
					+ " SET Nome = '" + professor.getName() + "'"
					+ " , Idade = " + professor.getAge()
					+ " , Sala = '" + professor.getClassRoom() + "'"
					+ " , Especialidade = '" + professor.getSpeciality() + "'"
					+ " WHERE Matricula = " + professor.getReg_number();
		}
		
	}

	@Override
	public boolean delete(Long id) {
		String sql = "DELETE FROM professor WHERE Matricula = " + id;

		return false;
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
