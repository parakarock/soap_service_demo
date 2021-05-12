package com.spring.nemo.soap_service_demo.soap.exeption;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode=FaultCode.CUSTOM, customFaultCode="{http://in28minutes.com/courses}001_COURSE_NOT_FOUND")
public class CourseNotFoundExeption extends RuntimeException {

	private static final long serialVersionUID = -619771957831956171L;

	public CourseNotFoundExeption(String message) {
		super(message);

	}

}
