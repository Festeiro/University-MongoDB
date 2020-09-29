package com.example.myapp.factory;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DatabaseConnection {
 
	
	 private static MongoDatabase db;
	 
	    public static MongoDatabase getConnection() {
	    	if(db == null) {
	    		ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
	    		CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
	    		CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
	    		MongoClientSettings clientSettings = MongoClientSettings.builder()
                        .applyConnectionString(connectionString)
                        .codecRegistry(codecRegistry)
                        .build();
	    		MongoClient mongoClient = MongoClients.create(clientSettings);
	    		db = mongoClient.getDatabase("universidade");
	    	}
	        return db;
	    }
	 
}