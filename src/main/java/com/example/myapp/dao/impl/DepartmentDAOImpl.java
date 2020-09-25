package com.example.myapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.myapp.dao.DepartmentDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Department;
import com.example.myapp.model.Professor;

public class DepartmentDAOImpl implements DepartmentDAO{

	public DepartmentDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private boolean exists(Department department) {
		
		String sql = "SELECT * FROM Departamento WHERE Numero = " + department.getDep_number();
		boolean exists = false;
		
		
		return exists;
	}
	
	@Override
	public void save(Department department) {
		
		String sql = "";
		boolean isUpdate = exists(department);
		Long matLider = null;
		
		if(department.getProfLeader() != null) {
			matLider = department.getProfLeader().getReg_number();
		}
		
		if(!isUpdate) {
			sql = "INSERT INTO Departamento (Numero, Nome, EscritorioPrincipal, MatLider)"
					+ " VALUES(" + department.getDep_number() + ", '" + department.getName() + "', '"
					+ department.getCentralOffice() + "', " + matLider + ")";
		}
		else {
			sql = "UPDATE Departamento"
					+ " SET Nome = '" + department.getName() + "'"
					+ " , EscritorioPrincipal = '" + department.getCentralOffice() + "'"
					+ " , MatLider = " + matLider
					+ " WHERE Numero = " + department.getDep_number();
		}
		
	}
	
	@Override
	public boolean delete(Long id) {
		String sql = "DELETE FROM Departamento WHERE Numero = " + id;

		return true;
	}
		
	@Override
	public ArrayList<Department> listAll(){
		
		String sql = "SELECT * FROM Departamento";
		ArrayList<Department> departments = new ArrayList<Department>();
		
		return departments;
	}
	
	public Department listByDepNumber(Long dep_number){
		
		String sql = "SELECT * FROM Departamento WHERE Numero = " + dep_number;
		Department department = new Department();
	
		return department;
	}
}
