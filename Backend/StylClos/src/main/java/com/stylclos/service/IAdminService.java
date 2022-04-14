package com.stylclos.service;

import java.util.List;

import com.stylclos.dto.TypeResponseDTO;

public interface IAdminService {
	
	int countUserByRole(String role);
	
	List<TypeResponseDTO> getAllTypes();
	
	int getCountOfCategory();
	
	int getCountOfTypes();

}
