package com.spring.nemo.soap_service_demo.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spring.nemo.soap_service_demo.soap.bean.Course;

@Component
public class CourseDetailsService {

	public enum Status{
		SUCCESS,FAILURE;
	}
	
	private static List<Course> courses = new ArrayList<>();
	private static int CourseCount = 4 ;
	static {
		Course course1 = new Course(1,"NeMo Course1","Hello");
		courses.add(course1);
		Course course2 = new Course(2,"NeMo Course2","Hello");
		courses.add(course2);
		Course course3 = new Course(3,"NeMo Course3","Hello");
		courses.add(course3);
		Course course4 = new Course(4,"NeMo Course4","Hello");
		courses.add(course4);
	}
	//course 1
	public Course findById(int id) {
		for(Course course:courses) {
			if(course.getId()==id) {
				return course;
			}
		}
		return null;
	}
	//course findById(int id)
	
	//courses
	public List<Course> findAll(){
		return courses;
	}
	//List<Course>findAll();
	public Status deleteById(int id) {
		Iterator<Course> iterator = courses.iterator();
		while(iterator.hasNext()) {
			Course course = iterator.next();
			if(course.getId()==id) {
				iterator.remove();
				return Status.SUCCESS;
			}
		}
		return Status.FAILURE;
	}
	//deletecourse
	
	public Course insertCourse(String name,String description) {
		Course newCourse = new Course(++CourseCount,name,description);
		courses.add(newCourse);
		return newCourse;
	}
	
	public Status updateCourse(Course data) {
		Iterator<Course> iterator = courses.iterator();
		while(iterator.hasNext()) {
			Course course = iterator.next();
			if(course.getId()==data.getId()) {
				course.setName(data.getName());
				course.setDescription(data.getDescription());
				return Status.SUCCESS;
			}
		}
		return Status.FAILURE;
	}
	//updating course & new course
}
