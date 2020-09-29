package com.example.myapp.dao.impl;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;

import com.example.myapp.dao.ProfessorDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Department;
import com.example.myapp.model.Participates;
import com.example.myapp.model.Professor;
import com.example.myapp.model.Project;
import com.example.myapp.model.Works;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class ProfessorDAOImpl implements ProfessorDAO{

	private MongoCollection<Professor> collection;
	private MongoCollection<Participates> participatesCollection;
	private MongoCollection<Project> projectCollection;
	private MongoCollection<Department> departmentCollection;
	private MongoCollection<Works> worksCollection;
	
	public ProfessorDAOImpl() {
		collection = DatabaseConnection.getConnection().getCollection("professor", Professor.class);
		participatesCollection = DatabaseConnection.getConnection().getCollection("participa", Participates.class);
		projectCollection = DatabaseConnection.getConnection().getCollection("projeto", Project.class);
		departmentCollection = DatabaseConnection.getConnection().getCollection("departamento", Department.class);
		worksCollection = DatabaseConnection.getConnection().getCollection("trabalha", Works.class);
	}

	private boolean exists(Professor professor) {
		
		Professor foundProfessor = collection.find(eq("reg_number", professor.getReg_number())).first();
		
		return foundProfessor != null;
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
		
		boolean isUpdate = exists(professor);
		
		if(!isUpdate) {
			generateReg_number(professor);
			collection.insertOne(professor);	
		}else {
			collection.findOneAndReplace(eq("reg_number", professor.getReg_number()), professor);
		}
	}

	@Override
	public boolean delete(Long id) {
		
		Participates participates = participatesCollection.find(eq("professor.reg_number", id)).first();
		Project project = projectCollection.find(eq("profLeader.reg_number", id)).first();
		Department department = departmentCollection.find(eq("profLeader.reg_number", id)).first();
		
		if(participates != null || project != null || department != null) {
			return false;
		}
		
		worksCollection.deleteMany(eq("professor.reg_number", id));
		collection.deleteOne(eq("reg_number", id));
		
		return true;
	}

	@Override
	public ArrayList<Professor> listAll(){
		
		ArrayList<Professor> professors = new ArrayList<Professor>();
		FindIterable<Professor> findIterable = collection.find();
		MongoCursor<Professor> cursor = findIterable.iterator();
		
		try {
			while(cursor.hasNext()) {
				professors.add(cursor.next());
			}
		} finally {
			cursor.close();
		}
		return professors;
	}
	
	public Professor listByRegNumber(Long reg_number){
		return collection.find(eq("reg_number", reg_number)).first();
	}
}
