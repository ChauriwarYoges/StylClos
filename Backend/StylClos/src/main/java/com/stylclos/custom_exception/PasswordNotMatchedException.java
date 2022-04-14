package com.stylclos.custom_exception;

@SuppressWarnings("serial")
public class PasswordNotMatchedException extends RuntimeException {
	public PasswordNotMatchedException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
}
