package com.stylclos.service;

import java.util.List;

import com.stylclos.dto.ProductResponseDTO;

public interface ISellerService {
	
	List<ProductResponseDTO> getAllProductBySellerId(int id);
	
	boolean deactivateSellerProfile(int id);
	
	
}
