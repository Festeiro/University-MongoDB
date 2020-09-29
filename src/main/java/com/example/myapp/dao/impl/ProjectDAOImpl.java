package com.example.myapp.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.example.myapp.dao.ProjectDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Project;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

	public class ProjectDAOImpl implements ProjectDAO{

		ProfessorDAOImpl professorDAOImpl = new ProfessorDAOImpl();
		private MongoCollection<Project> collection;
		
		public ProjectDAOImpl() {
			collection = DatabaseConnection.getConnection().getCollection("projeto", Project.class);
		}
		
		@Override
		public void save(Project project) {
			collection.insertOne(project);
		}

		@Override
		public boolean delete(String id) {
			collection.deleteOne(new Document("numero", new ObjectId(id)));
			return true;
		}

		@Override
		public ArrayList<Project> listAll(){
			ArrayList<Project> projects = new ArrayList<Project>();
			FindIterable<Project> findIterable = collection.find();
			MongoCursor<Project> cursor = findIterable.iterator();
			
			while(cursor.hasNext()) {
				projects.add(cursor.next());
			}
			return projects;
		}
		
		public Project listByProjectNumber(Long projectNumber){
			return null;
		}
		
		private boolean exists(Project project) {
			
			String sql = "SELECT * FROM Projeto WHERE Numero = " + project.getProjectNumber();
			boolean exists = false;
			
			
			return exists;
		}


}
