package com.example.myapp.dao.impl;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;

import com.example.myapp.dao.WorksDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Works;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class WorksDAOImpl implements WorksDAO{

	private MongoCollection<Works> collection;
	
	public WorksDAOImpl() {
		collection = DatabaseConnection.getConnection().getCollection("trabalha", Works.class);
	}
	
	private boolean exists(Works works) {
		
		Works foundWorks = collection.find(and(eq("department.dep_number", works.getDepartment().getDep_number()), 
				eq("professor.reg_number", works.getProfessor().getReg_number()))).first();
		
		return foundWorks != null;
	}
	
	@Override
	public void save(Works works) {
		
		String sql = "";		
		boolean isUpdate = exists(works);
		
		if(!isUpdate) {
			collection.insertOne(works);			
		}else {
			collection.findOneAndReplace(and(eq("department.dep_number", works.getDepartment().getDep_number()), 
					eq("professor.reg_number", works.getProfessor().getReg_number())), works);
		}
		
	}
	
	@Override
	public boolean delete(Long professorRegNumber, Long worksDepNumber) {
		collection.deleteOne(and(eq("department.dep_number", worksDepNumber), 
				eq("professor.reg_number", professorRegNumber)));
		return true;
	}
		
	@Override
	public ArrayList<Works> listByProfessorRegNumber(Long professorRegNumber){
		ArrayList<Works> workss = new ArrayList<Works>();
		FindIterable<Works> findIterable = collection.find();
		MongoCursor<Works> cursor = findIterable.iterator();
		
		while(cursor.hasNext()) {
			workss.add(cursor.next());
		}
		
		return workss;
	}
}
