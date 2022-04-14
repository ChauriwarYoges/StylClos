package com.stylclos.dto;

import com.stylclos.pojos.BaseEntity;
import com.stylclos.pojos.Category;
import com.stylclos.pojos.Type;

import lombok.Data;

@Data
public class CartResponseDTO extends BaseEntity {

	private String title;

	private String image;

	private Category category;

	private Type type;

	private String size;

	private double quantity;
	
	private double price;

	private int sellerId;

	String name, imgType;
	private byte[] imgData;

}
