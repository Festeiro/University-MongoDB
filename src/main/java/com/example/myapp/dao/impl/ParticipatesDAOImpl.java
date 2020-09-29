package com.example.myapp.dao.impl;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;

import com.example.myapp.dao.ParticipatesDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Participates;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class ParticipatesDAOImpl implements ParticipatesDAO{

	private MongoCollection<Participates> collection;
	
	public ParticipatesDAOImpl() {
		collection = DatabaseConnection.getConnection().getCollection("participa", Participates.class);
	}
	
	private boolean exists(Participates participates) {
		
		Participates foundParticipates = collection.find(and(eq("project.projectNumber", participates.getProject().getProjectNumber()), 
				eq("professor.reg_number", participates.getProfessor().getReg_number()))).first();
		
		return foundParticipates != null;
	}
	
	@Override
	public void save(Participates participates) {
		
		boolean isUpdate = exists(participates);
		
		if(!isUpdate) {
			collection.insertOne(participates);			
		}else {
			collection.findOneAndReplace(and(eq("project.projectNumber", participates.getProject().getProjectNumber()), 
					eq("professor.reg_number", participates.getProfessor().getReg_number())), participates);
		}
		
	}
	
	@Override
	public boolean delete(Long projectNumber, Long professorRegNumber) {
		collection.deleteOne(and(eq("project.projectNumber", projectNumber), 
				eq("professor.reg_number", professorRegNumber)));
		return true;
	}
		
	@Override
	public ArrayList<Participates> listByProjectNumber(Long projectNumber){
		ArrayList<Participates> participatess = new ArrayList<Participates>();
		FindIterable<Participates> findIterable = collection.find(eq("project.projectNumber", projectNumber));
		MongoCursor<Participates> cursor = findIterable.iterator();
		
		try {
			while(cursor.hasNext()) {
				participatess.add(cursor.next());
			}
		} finally {
			cursor.close();
		}
		return participatess;
	}
}
