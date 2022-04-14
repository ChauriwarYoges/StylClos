package com.stylclos.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stylclos.dto.ProductResponseDTO;
import com.stylclos.dto.TypeResponseDTO;
import com.stylclos.dto.UpdateProductDTO;
import com.stylclos.pojos.Category;
import com.stylclos.service.IProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
	
	@Value("${image.upload.location}")
	private String location;
	
	@Autowired
	private IProductService service;

	@GetMapping("/list")
	public ResponseEntity<?> getAllProducts() throws IOException {
		System.out.println("in list of all products ");
		List<ProductResponseDTO> pro = service.getAllProducts();
//		System.out.println(pro);
		return new ResponseEntity<>(pro, HttpStatus.OK);
	}

	@GetMapping("/productcount")
	public int getProductCount() {
		System.out.println("in product count ");
		return service.getProductCount();
	}
	
	
	@GetMapping("/sort/{category}")
	public ResponseEntity<?> getAllWomenProducts(@PathVariable String category) throws IOException {
		System.out.println("in list of all products by category : " + category);
		return new ResponseEntity<>(service.getProductsByCategory(category), HttpStatus.OK);
	}

	@PostMapping("/addproduct")
	public ResponseEntity<?> addNewProduct(@RequestParam("productDetails") String productDetails, @RequestParam("imageData") MultipartFile imageData) {
		System.out.println("in add new product with image");

		System.out.println("Product : " + productDetails + "\n" + "image Name : " + imageData.getOriginalFilename());

		return new ResponseEntity<>(service.addNewProduct(productDetails, imageData), HttpStatus.ACCEPTED);
	}
	

	@GetMapping("/categories")
	public List<Category> getAllCategories() {
		System.out.println("in get All Categories");
		
		List<Category> catgories = service.getAllCategories();

		if (catgories.isEmpty())
			return null;

		return catgories;

	}

	@GetMapping("/category/{id}")
	public Category getCategory(@PathVariable int id) {
		System.out.println("in category");
		Category c = service.getCategory(id);

		return c;
	}

	@GetMapping("/type/{id}")
	public TypeResponseDTO getType(@PathVariable int id) throws IOException {
		System.out.println("in type");
		TypeResponseDTO t = service.getType(id);

		return t;
	}

	// GET ALL TYPES IN PARTICULAR CATEGORY ID
	@GetMapping("/alltypes/{id}")
	public List<?> getAllType(@PathVariable int id) throws IOException {
		System.out.println("getting all types belonging to category id : " + id);
		List<TypeResponseDTO> typesInCategory = service.getAllTypes(id);

		if (typesInCategory.isEmpty())
			return null;

		return typesInCategory;
	}

	@GetMapping("/deleteproduct/{id}")
	public boolean deleteProduct(@PathVariable int id) {
		System.out.println("in remove Product " + id);
		return service.removeProduct(id);
	}

	@PostMapping("/updateproduct")
	public boolean updateProduct(@RequestParam String updateProduct, @RequestParam("imageData") MultipartFile imageData) throws IOException {
		System.out.println("in update Product");
		return service.updateProduct(updateProduct, imageData);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable int id) throws IOException {
		System.out.println("get product detail : " + id);
		ProductResponseDTO p = service.getProductById(id);
//		System.out.println(p);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}

}
