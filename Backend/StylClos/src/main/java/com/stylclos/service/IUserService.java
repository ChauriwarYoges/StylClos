package com.stylclos.service;

import javax.validation.Valid;

import com.stylclos.dto.AddressDTO;
import com.stylclos.dto.CreateUserAccountRequest;
import com.stylclos.dto.LoginResponse;
import com.stylclos.dto.ProfileDTO;
import com.stylclos.pojos.Address;

public interface IUserService {
	
	LoginResponse authenticateUser(String email, String password, String role);
	
	LoginResponse createNewAccount(@Valid CreateUserAccountRequest request);
	
	LoginResponse getUserUsingEmail(String email);
	
	boolean addAddress(AddressDTO address);
	
	Address getAddress(int id);
	
	boolean deleteAddress(int id);
	
	boolean deleteUser(int id);
	
	boolean updateProfile(ProfileDTO profile);
}
