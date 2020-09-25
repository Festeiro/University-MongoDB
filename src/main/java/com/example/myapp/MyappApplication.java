package com.example.myapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.myapp.factory.DatabaseConnection;

@SpringBootApplication
public class MyappApplication {

	public static void main(String[] args){
		SpringApplication.run(MyappApplication.class, args);
		
		DatabaseConnection.createConnection();
	}

}
