package com.stylclos.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeRequestDTO {

	@NotEmpty(message = "provide type name")
	private String typeName;

	@NotEmpty(message = "provide image")
	private String image;
	
	@NotEmpty(message = "provide Category")
	private String category;
	
}
