package com.example.myapp.factory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class DatabaseConnection {
 
	
	 private static MongoClient mClient;
	 private static MongoDatabase database;
	 
	    public static MongoDatabase getConnection() {
	        if (mClient == null) {
	            mClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
	            database = mClient.getDatabase("universidade");
	        }
	        return database;
	    }
	 
}