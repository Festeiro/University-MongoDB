package com.example.myapp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.example.myapp.dao.DepartmentDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Department;
import com.example.myapp.model.Professor;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class DepartmentDAOImpl implements DepartmentDAO{
	
	private MongoCollection<Document> collection;
	private MongoCollection<Document> profCollection;

	public DepartmentDAOImpl() {
		collection = DatabaseConnection.getConnection().getCollection("departamento");
		profCollection = DatabaseConnection.getConnection().getCollection("Professor");
	}
	
	private boolean exists(Department department) {
		
		String sql = "SELECT * FROM Departamento WHERE Numero = " + department.getDep_number();
		boolean exists = false;
		
		
		return exists;
	}
	
	@Override
	public void save(Department department) {
		boolean isUpdate = exists(department);
		
		Document dbDummy = new Document();
		Long number = department.getDep_number();
		String name = department.getName();
		String centralOffice = department.getCentralOffice();
		String profLeader = department.getProfLeader().getName();
		dbDummy.append("numero", number);
		dbDummy.append("nome",name);
		dbDummy.append("escritorioPrincipal", centralOffice);
//		dbDummy.append("matLider", profLeader);
		ArrayList<BasicDBObject> milestones = new ArrayList<>();
		milestones.add(new BasicDBObject("nome", department.getProfLeader().getName()));
		milestones.add(new BasicDBObject("idade", department.getProfLeader().getAge()));
		milestones.add(new BasicDBObject("sala", department.getProfLeader().getClassRoom()));
		milestones.add(new BasicDBObject("especialidade", department.getProfLeader().getSpeciality()));
		dbDummy.put("matLider", milestones);
		collection.insertOne(dbDummy);
	}
	
	@Override
	public boolean delete(String id) {
		collection.deleteOne(new Document("_id", new ObjectId(id)));

		return true;
	}
		
	@Override
	public ArrayList<Department> listAll(){
		ArrayList<Department> depList = new ArrayList<>();
		FindIterable<Document> iterable = collection.find();
		MongoCursor<Document> resultIterator = iterable.cursor();
		
		while(resultIterator.hasNext()) {
			Document doc = resultIterator.next();
			Long number = doc.getLong("numero");
			String name = doc.getString("name");
			String centralOffice = doc.getString("escritorioPrincipal");
			
			List<Document> list = (List<Document>)doc.get("matLider");
			Document docName = list.get(0);
			Document docAge = list.get(1);
			Document docRoom = list.get(2);
			Document docSpeciality = list.get(3);
			Professor profLeader = new Professor();
			profLeader.setName(docName.getString("name"));
			profLeader.setAge(docAge.getInteger("idade"));
			profLeader.setClassRoom(docRoom.getString("sala"));
			profLeader.setSpeciality(docSpeciality.getString("especialidade"));
			
			Department department = new Department();
			department.setDep_number(number);
			department.setName(name);
			department.setCentralOffice(centralOffice);
			department.setProfLeader(profLeader);
			depList.add(department);
		}
		
		return depList;
	}
	
	public Department listByDepNumber(Long dep_number){
		
		String sql = "SELECT * FROM Departamento WHERE Numero = " + dep_number;
		Department department = new Department();
	
		return department;
	}
}
