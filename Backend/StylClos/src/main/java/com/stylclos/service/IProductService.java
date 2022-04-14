package com.stylclos.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.stylclos.dto.ImageDTO;
import com.stylclos.dto.ProductResponseDTO;
import com.stylclos.dto.TypeResponseDTO;
import com.stylclos.pojos.Category;

public interface IProductService {
	List<ProductResponseDTO> getAllProducts() throws IOException;
	
	int getProductCount();
	
	List<ProductResponseDTO> getProductsByCategory(String cat) throws IOException;
	
	boolean addNewProduct(String productDetails, MultipartFile image);
	
	boolean removeProduct(int id);
	
	boolean updateProduct(String product, MultipartFile image) throws IOException;
	
	ProductResponseDTO getProductById(Integer id) throws IOException;
	
	Category getCategory(int id);
	
	TypeResponseDTO getType(int id) throws IOException;
	
	List<Category> getAllCategories();
	
	List<TypeResponseDTO> getAllTypes(int id) throws IOException;
	
}
