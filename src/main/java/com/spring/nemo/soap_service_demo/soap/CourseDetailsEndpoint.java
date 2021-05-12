package com.spring.nemo.soap_service_demo.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.in28minutes.courses.CourseDetails;
import com.in28minutes.courses.DeleteCourseDetailsRequest;
import com.in28minutes.courses.DeleteCourseDetailsResponse;
import com.in28minutes.courses.GetAllCourseDetailsRequest;
import com.in28minutes.courses.GetAllCourseDetailsResponse;
import com.in28minutes.courses.GetCourseDetailsRequest;
import com.in28minutes.courses.GetCourseDetailsResponse;
import com.in28minutes.courses.InsertCourseDetailsRequest;
import com.in28minutes.courses.InsertCourseDetailsResponse;
import com.in28minutes.courses.UpdateCourseDetailsRequest;
import com.in28minutes.courses.UpdateCourseDetailsResponse;
import com.spring.nemo.soap_service_demo.soap.bean.Course;
import com.spring.nemo.soap_service_demo.soap.exeption.CourseNotFoundExeption;
import com.spring.nemo.soap_service_demo.soap.service.CourseDetailsService;
import com.spring.nemo.soap_service_demo.soap.service.CourseDetailsService.Status;

@Endpoint
public class CourseDetailsEndpoint {

	@Autowired
	CourseDetailsService service;

	// method
	// input - GetCourseDetailsRequest
	// input - GetCourseDetailsResponse
	@PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDeatilsRequest(@RequestPayload GetCourseDetailsRequest request) {
		Course course = service.findById(request.getId());

		if (course == null) {
			throw new CourseNotFoundExeption("Invalid Course Id " + request.getId());
		}

		return mapCourseDetails(course);
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course));

		return response;
	}

	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for (Course course : courses) {
			CourseDetails mapCourse = mapCourse(course);
			response.getCourseDetails().add(mapCourse);
		}

		return response;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}

	@PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDeatilsRequest(
			@RequestPayload GetAllCourseDetailsRequest request) {
		List<Course> courses = service.findAll();
		return mapAllCourseDetails(courses);
	}

	@PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDeatilsRequest(@RequestPayload DeleteCourseDetailsRequest request) {
		Status status = service.deleteById(request.getId());
		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		response.setStatus(mapStatus(status));
		return response;
	}

	private com.in28minutes.courses.Status mapStatus(Status status) {
		if (status == Status.FAILURE) {
			return com.in28minutes.courses.Status.FAILURE;
		}
		return com.in28minutes.courses.Status.SUCCESS;
	}

	@PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "InsertCourseDetailsRequest")
	@ResponsePayload
	public InsertCourseDetailsResponse insertCourseDeatilsRequest(@RequestPayload InsertCourseDetailsRequest request) {
		Course result = service.insertCourse(request.getName(),request.getDescription());
		InsertCourseDetailsResponse response = new InsertCourseDetailsResponse();
		response.setId(result.getId());
		response.setName(result.getName());
		response.setDescription(result.getDescription());
		return response;
	}
	
	@PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "UpdateCourseDetailsRequest")
	@ResponsePayload
	public UpdateCourseDetailsResponse updateCourseDeatilsRequest(@RequestPayload UpdateCourseDetailsRequest request) {
		Course data = new Course(request.getId(),request.getName(),request.getDescription());
		Status status = service.updateCourse(data);
		UpdateCourseDetailsResponse response = new UpdateCourseDetailsResponse();
		response.setStatus(mapStatus(status));
		return response;
	}

}
