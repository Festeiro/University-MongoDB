package com.example.myapp.dao.impl;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;

import com.example.myapp.dao.StudentDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Student;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class StudentDAOImpl implements StudentDAO{

	private MongoCollection<Student> collection;
	
	public StudentDAOImpl() {
		collection = DatabaseConnection.getConnection().getCollection("estudante", Student.class);
	}
	
	private boolean exists(Student student) {
		
		Student foundStudent = collection.find(eq("reg_number", student.getReg_number())).first();
		
		return foundStudent != null;
	}
	
	public void generateReg_number(Student student) {
		
		FindIterable<Student> findIterable = collection.find().sort(new BasicDBObject( "reg_number" , -1 ) ).limit(1);
		MongoCursor<Student> cursor = findIterable.iterator();
		Long maxValue = 0L;
		while(cursor.hasNext()) {
			Student stud = cursor.next();
			maxValue = stud.getReg_number();
			
		}
		if(maxValue == null) {
			student.setReg_number(1L);
		} else {
			student.setReg_number(maxValue+1);
		}
	}
	
	@Override
	public void save(Student student) {
		boolean isUpdate = exists(student);
		
		if(!isUpdate) {
			generateReg_number(student);
			collection.insertOne(student);
		} else {
			collection.findOneAndReplace(eq("reg_number", student.getReg_number()), student);
		}
	}
	
	@Override
	public boolean delete(Long id) {
		collection.deleteOne(eq("reg_number", id));
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
		
		Student student = new Student();
		return student;
	}
}
