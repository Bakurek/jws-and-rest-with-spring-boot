package com.training.soap.webservices.soapcoursemanagement.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode="{http://mynamespace.com/courses}001_COURSE_NOT_FOUND")
public class CourseNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4485593547534244687L;

	public CourseNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
