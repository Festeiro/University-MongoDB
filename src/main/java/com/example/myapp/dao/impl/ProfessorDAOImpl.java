package com.example.myapp.dao.impl;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;

import org.bson.types.ObjectId;

import com.example.myapp.dao.ProfessorDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Professor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class ProfessorDAOImpl implements ProfessorDAO{

	private MongoCollection<Professor> collection;
	
	public ProfessorDAOImpl() {
		collection = DatabaseConnection.getConnection().getCollection("professor", Professor.class);
	}
	
	@Override
	public void save(Professor professor) {
		collection.insertOne(professor);	
	}

	@Override
	public void delete(ObjectId id) {
		collection.deleteOne(eq("_id", id));
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
