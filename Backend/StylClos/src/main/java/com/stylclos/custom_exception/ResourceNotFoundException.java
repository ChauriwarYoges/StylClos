package com.stylclos.custom_exception;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
	
	public ResourceNotFoundException() {
		// Default Constructor
		super("Looking for resource not found");
	
	}
	
}
