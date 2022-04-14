package com.stylclos.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {
	
	private int userId;
	
	@NotEmpty(message = "please enter area name")
	private String areaName;
	
	@NotEmpty(message = "please enter city")
	@NotNull
	private String city;
	
	@NotEmpty(message = "please enter state")
	@NotNull
	private String state;
	
	@NotEmpty(message = "please enter country")
	@NotNull
	private String country;	
	
}
