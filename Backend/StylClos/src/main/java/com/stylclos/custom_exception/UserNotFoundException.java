package com.stylclos.custom_exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
}
