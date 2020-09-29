package com.example.myapp.dao.impl;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;

import com.example.myapp.dao.DepartmentDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Department;
import com.example.myapp.model.Student;
import com.example.myapp.model.Works;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class DepartmentDAOImpl implements DepartmentDAO{
	
	private MongoCollection<Department> collection;
	private MongoCollection<Student> studCollection;
	private MongoCollection<Works> profCollection;
	
	public DepartmentDAOImpl() {
		collection = DatabaseConnection.getConnection().getCollection("departamento", Department.class);
		studCollection = DatabaseConnection.getConnection().getCollection("estudante", Student.class);
		profCollection = DatabaseConnection.getConnection().getCollection("trabalha", Works.class);
	}
	
	private boolean exists(Department department) {
		
		Department foundDepartment = collection.find(eq("dep_number", department.getDep_number())).first();
		
		return foundDepartment != null;
	}
	
	@Override
	public void save(Department department) {
		
		boolean isUpdate = exists(department);
		
		if(!isUpdate) {
			collection.insertOne(department);			
		}else {
			collection.findOneAndReplace(eq("dep_number", department.getDep_number()), department);
		}
	}
	
	@Override
	public boolean delete(Long id) {
		
		Student student = studCollection.find(eq("department.dep_number", id)).first();
		if(student != null) {
			return false;
		}
		
		Works works = profCollection.find(eq("department.dep_number", id)).first();
		if(works != null) {
			return false;
		}
		
		collection.deleteOne(eq("dep_number", id));
		return true;
	}
		
	@Override
	public ArrayList<Department> listAll(){
		ArrayList<Department> departments = new ArrayList<Department>();
		FindIterable<Department> findIterable = collection.find();
		MongoCursor<Department> cursor = findIterable.iterator();
		
		try {
			while(cursor.hasNext()) {
				departments.add(cursor.next());
			}
		} finally {
			cursor.close();
		}
		return departments;
	}
	
	public Department listByDepNumber(Long dep_number){
		
		Department department = new Department();
	
		return department;
	}
}
