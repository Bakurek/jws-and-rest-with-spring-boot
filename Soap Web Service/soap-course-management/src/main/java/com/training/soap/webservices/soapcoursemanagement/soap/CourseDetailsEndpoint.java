package com.training.soap.webservices.soapcoursemanagement.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.mynamespace.courses.CourseDetails;
import com.mynamespace.courses.DeleteCourseDetailsRequest;
import com.mynamespace.courses.DeleteCourseDetailsResponse;
import com.mynamespace.courses.GetAllCourseDetailsRequest;
import com.mynamespace.courses.GetAllCourseDetailsResponse;
import com.mynamespace.courses.GetCourseDetailsRequest;
import com.mynamespace.courses.GetCourseDetailsResponse;
import com.training.soap.webservices.soapcoursemanagement.bean.Course;
import com.training.soap.webservices.soapcoursemanagement.service.CourseDetailsService;
import com.training.soap.webservices.soapcoursemanagement.service.CourseDetailsService.Status;
import com.training.soap.webservices.soapcoursemanagement.soap.exception.CourseNotFoundException;

@Endpoint
public class CourseDetailsEndpoint {
	
	@Autowired
	CourseDetailsService service;
	
	//method
	//input - request
	//output - response
	
	//http://mynamespace.com/courses
	//GetCourseDetailsRequest
	
	@PayloadRoot(namespace="http://mynamespace.com/courses",
			localPart="GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse 
		processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		
		Course course = service.findById(request.getId());
		
		if(course==null)
			throw new CourseNotFoundException("Invalid Course ID " + request.getId());
		
		return mapCourseDetails(course);
	}
	
	@PayloadRoot(namespace="http://mynamespace.com/courses",
			localPart="DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse 
		deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {
		
		Status status = service.deleteById(request.getId());
		
		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		response.setStatus(mapStatus(status));
		
		return response;
	}

	@PayloadRoot(namespace="http://mynamespace.com/courses",
			localPart="GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse 
		processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {
		
		List<Course> courses = service.findAll();
		
		return mapAllCourseDetails(courses);
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course));
		return response;
	}
	
	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for(Course course:courses) {
			CourseDetails mapCourse = mapCourse(course);
			response.getCourseDetails().add(mapCourse);
		}
		return response;
	}


	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails =  new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}
	
	private com.mynamespace.courses.Status mapStatus(Status status) {
		if(status==Status.FAILURE)
			return com.mynamespace.courses.Status.FAILURE;
		return com.mynamespace.courses.Status.SUCCESS;
	}
}
