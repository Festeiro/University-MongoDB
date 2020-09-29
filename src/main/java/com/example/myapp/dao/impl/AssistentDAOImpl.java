package com.example.myapp.dao.impl;

import static com.mongodb.client.model.Filters.eq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.myapp.dao.AssistentDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Assistent;
import com.example.myapp.model.Project;
import com.example.myapp.model.Student;
import com.example.myapp.model.Works;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class AssistentDAOImpl implements AssistentDAO{

	ProjectDAOImpl projectDAO = new ProjectDAOImpl();
	StudentDAOImpl studentDAO = new StudentDAOImpl();
	ProfessorDAOImpl profDAO = new ProfessorDAOImpl();
	private MongoCollection<Assistent> collection;
	
	public AssistentDAOImpl() {
		collection = DatabaseConnection.getConnection().getCollection("assiste", Assistent.class);
	}

	private boolean exists(Assistent assistent) {
		
		Assistent foundAssistent = collection.find(eq("student.reg_number", assistent.getStudent().getReg_number())).first();
		return foundAssistent != null;
	}
	
	@Override
	public void save(Assistent assistent) {
		boolean isUpdate = exists(assistent);
		
		if(!isUpdate) {
			collection.insertOne(assistent);
		} else {
			collection.findOneAndReplace(eq("student.reg_number", assistent.getStudent().getReg_number()), assistent);
		}
		
		
	}

	@Override
	public boolean delete(Long studentRegNumber) {
		collection.deleteOne(eq("student.reg_number", studentRegNumber));
		return true;
	}

	@Override
	public ArrayList<Assistent> listByStudentRegNumber(Long studentRegNumber) {
		ArrayList<Assistent> assists = new ArrayList<Assistent>();
		FindIterable<Assistent> findIterable = collection.find();
		MongoCursor<Assistent> cursor = findIterable.iterator();
		try {
	
			while(cursor.hasNext()) {
				assists.add(cursor.next());
			}
		} finally {
			cursor.close();
		}
		return assists;
	}

}
