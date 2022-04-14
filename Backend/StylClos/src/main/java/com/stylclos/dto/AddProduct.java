package com.stylclos.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProduct {
	
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
	
	@NotNull
	private String email;

}
