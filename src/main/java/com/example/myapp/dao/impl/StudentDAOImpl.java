package com.example.myapp.dao.impl;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;

import com.example.myapp.dao.StudentDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Assistent;
import com.example.myapp.model.Student;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class StudentDAOImpl implements StudentDAO{

	private MongoCollection<Student> collection;
	private MongoCollection<Assistent> assistCollection;
	
	public StudentDAOImpl() {
		collection = DatabaseConnection.getConnection().getCollection("estudante", Student.class);
		assistCollection = DatabaseConnection.getConnection().getCollection("assiste", Assistent.class);
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
		
		Assistent studentAssistent = assistCollection.find(eq("student.reg_number", id)).first();
		if(studentAssistent != null) {
			return false;
		}
		Student studentAdvisor = collection.find(eq("advisor.reg_number", id)).first();
		if(studentAdvisor != null) {
			return false;
		}
		
		collection.deleteOne(eq("reg_number", id));
		return true;
	}
		
	@Override
	public ArrayList<Student> listAll(){
		ArrayList<Student> students = new ArrayList<Student>();
		FindIterable<Student> findIterable = collection.find();
		MongoCursor<Student> cursor = findIterable.iterator();
		
		try {
			while(cursor.hasNext()) {
				students.add(cursor.next());
			}
		} finally {
			cursor.close();
		}
		return students;
	}
	
	public Student listByRegNumber(Long reg_number){
		
		Student student = new Student();
		return student;
	}
}
