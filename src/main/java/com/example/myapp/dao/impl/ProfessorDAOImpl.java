package com.example.myapp.dao.impl;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.example.myapp.dao.ProfessorDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Professor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class ProfessorDAOImpl implements ProfessorDAO{

	private MongoCollection<Document> collection;
	
	public ProfessorDAOImpl() {
		collection = DatabaseConnection.getConnection().getCollection("Professor");
	}
	
	@Override
	public void save(Professor professor) {
		Document dbDummy = new Document();
		String name = professor.getName();
		Integer age = professor.getAge();
		String room = professor.getClassRoom();
		String speciality = professor.getSpeciality();
		dbDummy.append("nome", name);
		dbDummy.append("idade", age);
		dbDummy.append("sala", room);
		dbDummy.append("especialidade", speciality);
		collection.insertOne(dbDummy);
		
	}

	@Override
	public void delete(String id) {
		collection.deleteOne(new Document("_id", new ObjectId(id)));
	}

	@Override
	public ArrayList<Professor> listAll(){
		int defaultValue = 0;
		ArrayList<Professor> professorList = new ArrayList<>();
		FindIterable<Document> a = collection.find();
		MongoCursor<Document> resultIterator = a.cursor();
		
		while(resultIterator.hasNext()) {
			Document doc = resultIterator.next();
			ObjectId reg_number = doc.getObjectId("_id"); 
			String name = doc.getString("nome");
			Integer age = doc.getInteger("idade", defaultValue);
			String classRoom = doc.getString("sala");
			String speciality = doc.getString("especialidade");

			Professor professor = new Professor();
			professor.setReg_number(reg_number.toString());
			professor.setName(name);
			professor.setAge(age);
			professor.setClassRoom(classRoom);
			professor.setSpeciality(speciality);
			professorList.add(professor);
		}
		return professorList;
	}
	
	public Professor listByRegNumber(Long reg_number){
		
		Professor prof = new Professor();
		
		
		return prof;
	}
}
