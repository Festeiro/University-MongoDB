package com.example.myapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.myapp.dao.AssistentDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Assistent;
import com.example.myapp.model.Project;
import com.example.myapp.model.Student;

public class AssistentDAOImpl implements AssistentDAO{

	ProjectDAOImpl projectDAO = new ProjectDAOImpl();
	StudentDAOImpl studentDAO = new StudentDAOImpl();
	ProfessorDAOImpl profDAO = new ProfessorDAOImpl();
	
	public AssistentDAOImpl() {
	}

	private boolean exists(Assistent assistent) {
		
		String sql = "SELECT * FROM Assiste WHERE numProjeto = " + assistent.getProject().getProjectNumber()
					+ " AND matsupervisor = " + assistent.getProfessor().getReg_number();
		boolean exists = false;
		
		
		return exists;
	}
	
	@Override
	public void save(Assistent assistent) {
		String sql = "";
		boolean isUpdate = exists(assistent);
		
		if(!isUpdate) {
			sql = "INSERT INTO Assiste (NumProjeto, MatEstudante, MatSupervisor)"
					+ "VALUES("	+ assistent.getProject().getProjectNumber() + ", " 
					+ assistent.getStudent().getReg_number() + ", "
					+ assistent.getProfessor().getReg_number() + ")";
		} else {
			sql = "Update Assiste"
					+ " SET MatEstudante = " + assistent.getStudent().getReg_number()
					+ " AND MatSupervisor = " + assistent.getProfessor().getReg_number();
		}
		
		
	}

	@Override
	public boolean delete(Long studentRegNumber) {
		String sql = "DELETE FROM Assiste WHERE matEstudante = " + studentRegNumber;
		
		return true;
	}

	@Override
	public ArrayList<Assistent> listByStudentRegNumber(Long studentRegNumber) {
		String sql = "SELECT * FROM Assiste WHERE MatEstudante = " + studentRegNumber;
		ArrayList<Assistent> assistentList = new ArrayList<Assistent>();
		
		return assistentList;
	}

}
