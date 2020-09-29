package com.example.myapp.model;

import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

public class Project {

	private Long projectNumber;
	private String financier;
	private float budget;
	private String startDate;
	private String endDate;
	private Professor profLeader;
	


	public Project(Long projectNumber, String financier, String startDate, String endDate, float budget, Professor profLeader) {
		this.projectNumber = projectNumber;
		this.financier = financier;
		this.budget = budget;
		this.startDate = startDate;
		this.endDate = endDate;
		this.profLeader = profLeader;
	}
	
	public Professor getProfLeader() {
		return profLeader;
	}
	
	public void setProfLeader(Professor profLeader) {
		this.profLeader = profLeader;
	}
	
	public float getBudget() {
		return budget;
	}
	
	public void setBudget(float budget) {
		this.budget = budget;
	}

	public Long getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(Long projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getFinancier() {
		return financier;
	}

	public void setFinancier(String financier) {
		this.financier = financier;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Project() {}

}