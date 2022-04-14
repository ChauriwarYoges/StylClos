package com.stylclos.dto;

import com.stylclos.pojos.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeResponseDTO extends BaseEntity {
	
	private String typeName;

	private String image;

	private int categoryId;
	
	private String name, imgType;
	private byte[] imgData;
	
}
