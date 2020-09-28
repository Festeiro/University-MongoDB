package com.example.myapp.model;

import org.bson.types.ObjectId;

public class Professor{

	private ObjectId id;
	private String reg_number;
	private String name;
	private Integer age;
	private String speciality;
	private String classRoom;


	public Professor(String name, Integer age, String speciality, String classRoom) {
		this.name = name;
		this.age = age;
		this.speciality = speciality;
		this.classRoom = classRoom;
	}
	
	public Professor() {
		// TODO Auto-generated constructor stub
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	public String getReg_number() {
		return reg_number;
	}

	public void setReg_number(String reg_number) {
		this.reg_number = reg_number;
	}

	public String getClassRoom() {
		return classRoom;
	}


}

