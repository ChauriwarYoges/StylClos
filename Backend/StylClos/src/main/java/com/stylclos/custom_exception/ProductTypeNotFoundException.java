package com.stylclos.custom_exception;

@SuppressWarnings("serial")
public class ProductTypeNotFoundException extends RuntimeException {

	public ProductTypeNotFoundException() {
		super("Product Type Not Available");
	}
	
	public ProductTypeNotFoundException(String msg) {
		super(msg);
	}
	
}
