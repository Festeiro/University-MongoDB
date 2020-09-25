package com.example.myapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.myapp.dao.WorksDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Department;
import com.example.myapp.model.Professor;
import com.example.myapp.model.Works;

public class WorksDAOImpl implements WorksDAO{

	public WorksDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private boolean exists(Works works) {
		
		String sql = "SELECT * FROM Trabalha WHERE NumDepartamento = " + works.getDepartment().getDep_number()
				+ " AND MatProfessor = " + works.getProfessor().getReg_number() ;
		boolean exists = false;
		
		return exists;
	}
	
	@Override
	public void save(Works works) {
		
		String sql = "";		
		boolean isUpdate = exists(works);
		
		if(!isUpdate) {
			sql = "INSERT INTO Trabalha (MatProfessor, NumDepartamento, PercentagemTempo)"
					+ " VALUES( " + works.getProfessor().getReg_number() + ", " + works.getDepartment().getDep_number() + ", " + works.getTimePercentage() + " )";
		}
		else {
			sql = "UPDATE Trabalha"
					+ " SET PercentagemTempo = " + works.getTimePercentage()
					+ " WHERE MatProfessor = " + works.getProfessor().getReg_number()
					+ " AND NumDepartamento = " + works.getDepartment().getDep_number();
		}
		
	}
	
	@Override
	public boolean delete(Long professorRegNumber, Long departmentDepNumber) {
		String sql = "DELETE FROM Trabalha WHERE MatProfessor = " + professorRegNumber + " AND NumDepartamento = " + departmentDepNumber;

		return true;
	}
		
	@Override
	public ArrayList<Works> listByProfessorRegNumber(Long professorRegNumber){
		String sql = "SELECT * FROM Trabalha WHERE MatProfessor = " + professorRegNumber;
		ArrayList<Works> workss = new ArrayList<Works>();
		return workss;
	}
}
