package com.example.myapp.dao.impl;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;

import com.example.myapp.dao.ProjectDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Assistent;
import com.example.myapp.model.Project;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class ProjectDAOImpl implements ProjectDAO{

	ProfessorDAOImpl professorDAOImpl = new ProfessorDAOImpl();
	private MongoCollection<Project> collection;
	private MongoCollection<Assistent> assistCollection;
	
	public ProjectDAOImpl() {
		collection = DatabaseConnection.getConnection().getCollection("projeto", Project.class);
		assistCollection = DatabaseConnection.getConnection().getCollection("assiste", Assistent.class);
	}
	
	private boolean exists(Project project) {
		
		Project foundProject = collection.find(eq("projectNumber", project.getProjectNumber())).first();
		
		return foundProject != null;
	}
	
	@Override
	public void save(Project project) {	
		
		boolean isUpdate = exists(project);
		
		if(!isUpdate) {
			collection.insertOne(project);				
		}else {
			collection.findOneAndReplace(eq("projectNumber", project.getProjectNumber()), project);
		}
	}

	@Override
	public boolean delete(Long id) {
		
		Assistent student = assistCollection.find(eq("project.projectNumber", id)).first();
		if(student != null) {
			return false;
		}
		collection.deleteOne(eq("projectNumber", id));
		return true;
	}

	@Override
	public ArrayList<Project> listAll(){
		ArrayList<Project> projects = new ArrayList<Project>();
		FindIterable<Project> findIterable = collection.find();
		MongoCursor<Project> cursor = findIterable.iterator();
		try {
			while(cursor.hasNext()) {
				projects.add(cursor.next());
			}
		} finally {
			cursor.close();
		}
		return projects;
	}
	
	public Project listByProjectNumber(Long projectNumber){
		return null;
	}

}
