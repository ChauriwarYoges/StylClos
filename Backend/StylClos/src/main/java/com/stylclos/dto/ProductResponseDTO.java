package com.stylclos.dto;

import com.stylclos.pojos.BaseEntity;
import com.stylclos.pojos.Category;
import com.stylclos.pojos.Type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO extends BaseEntity {
	
	private String title;
	
	private String image;
	
	private Category category;
	
	private Type type;
	
	private String size;
	
	private int quantity;
	
	private int sellerId;
	
	private double price;
	
	private String name, imgType;
	private byte[] imgData;
}
