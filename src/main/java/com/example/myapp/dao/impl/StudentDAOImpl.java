package com.example.myapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.myapp.dao.StudentDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Department;
import com.example.myapp.model.Student;

public class StudentDAOImpl implements StudentDAO{

	public StudentDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void save(Student student) {
		
		String sql = "";
		Long matAconselhador = null;
		Long numDepartamento = null;
		
		if(student.getAdvisor() != null) {
			matAconselhador = student.getAdvisor().getReg_number();
		}
		
		if(student.getDepartment() != null) {
			numDepartamento = student.getDepartment().getDep_number();
		}
		
		
		if(student.getReg_number() == null) {
			sql = "INSERT INTO Estudante (Nome, Idade, TipoCurso, MatAconselhador, NumDepartamento)"
					+ " VALUES('" + student.getName() + "', " + student.getAge() + ", '" + student.getCourse() + "'"
							+ ", " + matAconselhador + ", " + numDepartamento + ")";
		}
		else {
			sql = "UPDATE Estudante"
					+ " SET Nome = '" + student.getName() + "'"
					+ " , Idade = " + student.getAge()
					+ " , TipoCurso = '" + student.getCourse() + "'"
					+ " , MatAconselhador = " + matAconselhador
					+ " , NumDepartamento = " + numDepartamento
					+ " WHERE Matricula = " + student.getReg_number();
		}
		
	}
	
	@Override
	public boolean delete(Long id) {
		String sql = "DELETE FROM Estudante WHERE Matricula = " + id;

		
		return true;
	}
		
	@Override
	public ArrayList<Student> listAll(){
		String sql = "SELECT * FROM Estudante";
		ArrayList<Student> students = new ArrayList<Student>();
		return students;
	}
	
	public Student listByRegNumber(Long reg_number){
		
		String sql = "SELECT * FROM Estudante WHERE matricula = " + reg_number;
		Student student = new Student();
		return student;
	}
}
