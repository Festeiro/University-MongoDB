package com.example.myapp.factory;

import java.util.List;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class DatabaseConnection {
 
	public static void createConnection() {
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		 
		@SuppressWarnings("deprecation")
		List<String> databases = mongoClient.getDatabaseNames();
		 
		for (String dbName : databases) {
		    System.out.println("- Database: " + dbName);
		     
		    @SuppressWarnings("deprecation")
			DB db = mongoClient.getDB(dbName);
		     
		    Set<String> collections = db.getCollectionNames();
		    for (String colName : collections) {
		        System.out.println("\t + Collection: " + colName);
		    }
		}
		 
		mongoClient.close();
         
    }
}