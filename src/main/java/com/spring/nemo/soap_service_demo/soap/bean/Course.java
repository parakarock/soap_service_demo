package com.spring.nemo.soap_service_demo.soap.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Course {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String description;

	public Course(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}


