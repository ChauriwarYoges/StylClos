package com.stylclos.custom_exception;

@SuppressWarnings("serial")
public class ProductCategoryNotFoundException extends RuntimeException {

	public ProductCategoryNotFoundException() {
		super("Category Not Available");
	}
	
	public ProductCategoryNotFoundException(String msg) {
		super(msg);
	}
	
}
