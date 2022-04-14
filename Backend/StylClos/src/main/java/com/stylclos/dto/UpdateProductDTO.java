package com.stylclos.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductDTO {

	@NotNull
	private int id;
	
	@NotNull
	private String title;
	
	@NotNull
	private int category;
	
	@NotNull
	private int type;
	
	@NotNull
	private String size;
	
	@NotNull
	private int qty;
	
	@NotNull
	private double price;
	
}
