package com.example.myapp.dao.impl;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.example.myapp.dao.StudentDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Student;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class StudentDAOImpl implements StudentDAO{

	private MongoCollection<Student> collection;
	
	public StudentDAOImpl() {
		collection = DatabaseConnection.getConnection().getCollection("estudante", Student.class);
	}
	
	@Override
	public void save(Student student) {
		
		collection.insertOne(student);
	}
	
	@Override
	public boolean delete(String id) {
		collection.deleteOne(new Document("_id", new ObjectId(id)));
		return true;
	}
		
	@Override
	public ArrayList<Student> listAll(){
		ArrayList<Student> students = new ArrayList<Student>();
		FindIterable<Student> findIterable = collection.find();
		MongoCursor<Student> cursor = findIterable.iterator();
		
		while(cursor.hasNext()) {
			students.add(cursor.next());
		}
		return students;
	}
	
	public Student listByRegNumber(Long reg_number){
		
		String sql = "SELECT * FROM Estudante WHERE matricula = " + reg_number;
		Student student = new Student();
		return student;
	}
}
