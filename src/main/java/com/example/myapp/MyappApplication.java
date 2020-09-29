package com.example.myapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyappApplication {

	public static void main(String[] args){
		SpringApplication.run(MyappApplication.class, args);
		
//		MongoClient mongoClient = DatabaseConnection.getConnection();
//		MongoDatabase database = mongoClient.getDatabase("universidade");
//		
//		MongoCollection<Document> collection = database.getCollection("Professor");
//		Document dbDummy = new Document();
//		String nome = "marcelo";
//		dbDummy.append("nome", nome);
//		collection.insertOne(dbDummy);
	}

}
