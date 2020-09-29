package com.example.myapp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.example.myapp.dao.DepartmentDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Department;
import com.example.myapp.model.Professor;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class DepartmentDAOImpl implements DepartmentDAO{
	
	private MongoCollection<Department> collection;

	public DepartmentDAOImpl() {
		collection = DatabaseConnection.getConnection().getCollection("departamento", Department.class);
	}
	
	private boolean exists(Department department) {
		boolean exists = false;
		return exists;
	}
	
	@Override
	public void save(Department department) {
		boolean isUpdate = exists(department);
		
		collection.insertOne(department);
	}
	
	@Override
	public boolean delete(String id) {
		collection.deleteOne(new Document("numero", new ObjectId(id)));

		return true;
	}
		
	@Override
	public ArrayList<Department> listAll(){
		ArrayList<Department> departments = new ArrayList<Department>();
		FindIterable<Department> findIterable = collection.find();
		MongoCursor<Department> cursor = findIterable.iterator();
		
		while(cursor.hasNext()) {
			departments.add(cursor.next());
		}
		
		return departments;
	}
	
	public Department listByDepNumber(Long dep_number){
		
		String sql = "SELECT * FROM Departamento WHERE Numero = " + dep_number;
		Department department = new Department();
	
		return department;
	}
}
