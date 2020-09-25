package com.example.myapp.factory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class DatabaseConnection {
 
	
	 private static MongoClient mClient;
	 
	    public static MongoClient getConnection() {
	        if (mClient == null) {
	            mClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
	        }
	        return mClient;
	    }
	 
}