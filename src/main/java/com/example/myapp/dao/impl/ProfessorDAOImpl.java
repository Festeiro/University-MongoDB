package com.example.myapp.dao.impl;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;

import org.bson.types.ObjectId;

import com.example.myapp.dao.ProfessorDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Professor;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class ProfessorDAOImpl implements ProfessorDAO{

	private MongoCollection<Professor> collection;
	
	public ProfessorDAOImpl() {
		collection = DatabaseConnection.getConnection().getCollection("professor", Professor.class);
	}
	
	public void generateReg_number(Professor professor) {
		FindIterable<Professor> findIterable = collection.find().sort(new BasicDBObject( "reg_number" , -1 ) ).limit(1);
		MongoCursor<Professor> cursor = findIterable.iterator();
		Long maxValue = 0L;
		while(cursor.hasNext()) {
			Professor prof = cursor.next();
			maxValue = prof.getReg_number();
			
		}
		if(maxValue == null) {
			professor.setReg_number(1L);
		} else {
			professor.setReg_number(maxValue+1);
		}
	}
	
	@Override
	public void save(Professor professor) {
		generateReg_number(professor);
		collection.insertOne(professor);	
	}

	@Override
	public void delete(Long id) {
		collection.deleteOne(eq("reg_number", id));
	}

	@Override
	public ArrayList<Professor> listAll(){
		
		ArrayList<Professor> professors = new ArrayList<Professor>();
		FindIterable<Professor> findIterable = collection.find();
		MongoCursor<Professor> cursor = findIterable.iterator();
		
		while(cursor.hasNext()) {
			professors.add(cursor.next());
		}
		
		return professors;
	}
	
	public Professor listByRegNumber(Long reg_number){
		return collection.find(eq("reg_number", reg_number)).first();
	}
}
