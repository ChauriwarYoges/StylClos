package com.stylclos.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.stylclos.pojos.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserAccountRequest {
	@NotEmpty(message = "please provide email address")
	@NotNull(message = "please provide email address")
	private String email;
	
	@NotEmpty(message = "please provide name")
	@NotNull(message = "please provide name")
	private String name;
	
	private String registerDate;
	
	@NotEmpty(message = "please provide password")
	@NotNull(message = "please provide password")
	private String password;
	
	@NotNull(message = "please provide role of user")
	private String role;
	
	@NotEmpty(message = "please provide confirm password")
	@NotNull(message = "please provide confirm password")
	private String confirmPassword;
	
	private List<Address> address;
}
