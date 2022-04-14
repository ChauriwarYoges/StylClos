package com.stylclos.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProfileDTO {
	
	@NotNull
	private int userId;
	
	@NotEmpty(message = "please provide name")
	private String name;
	
	private int addId;
	
	@NotEmpty(message = "please enter area name")
	private String areaName;
	
	@NotEmpty(message = "please enter city")
	private String city;
	
	@NotEmpty(message = "please enter state")
	private String state;
	
	private String country;	
}
