package com.stylclos.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stylclos.custom_exception.ResourceNotFoundException;
import com.stylclos.dao.CartRepository;
import com.stylclos.dao.CategoryRepository;
import com.stylclos.dao.ProductRepository;
import com.stylclos.dao.TypeRepository;
import com.stylclos.dto.TypeDTO;
import com.stylclos.dto.TypeRequestDTO;
import com.stylclos.dto.TypeResponseDTO;
import com.stylclos.pojos.Cart;
import com.stylclos.pojos.Category;
import com.stylclos.pojos.Product;
import com.stylclos.pojos.Type;
import com.stylclos.service.IAdminService;
import com.stylclos.service.IProductService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
@Service
@Transactional
public class AdminController {

	@Value("${image.upload.location}")
	private String location;

	@Autowired
	private IAdminService service;

	@Autowired
	private CategoryRepository catRepo;

	@Autowired
	private TypeRepository typeRepo;

	@Autowired
	private ProductRepository proRepo;

	@Autowired
	private CartRepository crtRepo;

	@Autowired
	private IProductService proService;

	/*
	 * BELOW MAPPING ARE ADDING NEW CATEGORY, TYPE TO DB
	 */
	@PostMapping("/addcategory")
	public boolean addNewCategory(@RequestBody Category newCat) {

		System.out.println("in addCategory() ");

		Category c = catRepo.findByCategory(newCat.getCategory());

		if (c != null)
			throw new RuntimeException("Category already exits");

		catRepo.save(newCat);

		return true;

	}

	@PostMapping("/addtype")
	public boolean addNewType(@RequestParam String typeDetails, @RequestParam MultipartFile typeImage) {

		try {
			TypeDTO newType = new ObjectMapper().readValue(typeDetails, TypeDTO.class);
			System.out.println("in addNewType() ");

			Type t = typeRepo.findByTypeName(newType.getTypeName());

//		System.out.println("search in DB : " + t); It will throw StackOverFlowException: null

			if (t != null)
				throw new RuntimeException("Type already exits");

			Category c = catRepo.getById(newType.getCategory());

			if (c == null)
				return false;

			Type addType = new Type();
			addType.setTypeName(newType.getTypeName());
			addType.setImage(typeImage.getOriginalFilename());
			addType.setCat(c);

			// STORE IMAGE AT LOCATION
			typeImage.transferTo(new File(location, typeImage.getOriginalFilename()));

			typeRepo.save(addType);

			return true;
		} catch (Exception e) {
			System.out.println("Error While Adding New Type : " + e.getMessage());
		}

//		addType.getProducts().size();

		return false;
	}

	/*
	 * BELOW MAPPING ARE UPDATE EXISITING CATEGORY, TYPE
	 */

	@PostMapping("/update/category")
	public boolean updateExistingCategory(@RequestBody Category cat) {

		System.out.println("in update category : " + cat.getId());
		Category c = catRepo.getById(cat.getId());

		if (c == null)
			return false;

		c.setCategory(cat.getCategory());

		catRepo.save(c);

		return true;
	}

	@PostMapping("/update/type/{id}")
	public boolean updateExisitingType(@PathVariable int id, @RequestParam("updatedType") String updatedType,
			@RequestParam("typeImage") MultipartFile typeImage) {

		try {
			TypeDTO trd = new ObjectMapper().readValue(updatedType, TypeDTO.class);

			Type t = typeRepo.getById(id);

			if (t == null)
				return false;

			t.setTypeName(trd.getTypeName());
			
			if (!t.getImage().equals(typeImage.getOriginalFilename())) {
				File file = new File(location + t.getImage());
				if (file.exists())
					file.delete();
			}
			
			if (typeImage != null) {
				t.setImage(typeImage.getOriginalFilename());

				typeImage.transferTo(new File(location + t.getImage()));
			}

			t.setTypeName(trd.getTypeName());
			t.setCat(catRepo.findById(trd.getCategory())
					.orElseThrow(() -> new ResourceNotFoundException("Unable to get Category")));
			typeRepo.save(t);

			return true;

		} catch (Exception e) {
			System.out.println("Exception in updating Type : " + e.getMessage());
		}

		return false;
	}

	/*
	 * BELOW MAPPING ARE DELETING EXISTING CATEGORY, TYPE
	 */

	@GetMapping("/deletecategory/{id}")
	public boolean deleteExistingCategory(@PathVariable int id) {
		System.out.println("in delete category : " + id);
		Category c = catRepo.getById(id);

		if (c == null)
			return false;

		List<Type> types = typeRepo.getTypesByCategoryId(id);

		types.forEach(t -> {
			List<Product> pros = proRepo.getProductsByTopicId(t.getId());
			pros.forEach(p -> {
				List<Cart> cart = crtRepo.findByProductId(p.getId());
				cart.forEach(ca -> crtRepo.delete(ca));
				proRepo.delete(p);
			});
		});

		catRepo.delete(c);
		return true;
	}

	@GetMapping("/deletetype/{id}")
	public boolean deleteExistingType(@PathVariable int id) {
		System.out.println("in delete Type : " + id);
		Type t = typeRepo.getById(id);
		System.out.println("Deleted Type : " + t.getTypeName());

		List<Product> pros = proRepo.getProductsByTopicId(id);

		pros.forEach(p -> {
			List<Cart> cart = crtRepo.findByProductId(p.getId());
			cart.forEach(c -> crtRepo.delete(c));

			File proFile = new File(location + p.getImage());
			if (proFile.exists())
				proFile.delete();

			proRepo.delete(p);
		});

		File file = new File(location + t.getImage());
		if (file.exists())
			file.delete();

		typeRepo.delete(t);
		return true;
	}

	/*
	 * RETRIEVE CATEGORY TYPE DATA BY ID
	 */
	@GetMapping("/type/{id}")
	public ResponseEntity<?> getTypeById(@PathVariable int id) {
		System.out.println("in get type by id : " + id);

		Type t = typeRepo.getById(id);

		if (t == null)
			return null;

		TypeResponseDTO trd = new TypeResponseDTO();
		trd.setId(t.getId());
		trd.setTypeName(t.getTypeName());
		trd.setCategoryId(t.getCat().getId());
		trd.setImage(t.getImage());

		Path path = Paths.get(location + t.getImage());
		try {
			trd.setImgData(Files.readAllBytes(path));
			trd.setImgType(Files.probeContentType(path));
		} catch (IOException e) {
			System.out.println("error while retrieving type image : " + id);
		}

		return ResponseEntity.status(HttpStatus.OK).body(trd);
	}

	@GetMapping("/{role}")
	public int getCountByRole(@PathVariable String role) {
		System.out.println("in counting user's by role : " + role);

		return service.countUserByRole(role);
	}

	@GetMapping("/getalltypes")
	public List<TypeResponseDTO> getAllTypes() {
		System.out.println("all types in DB : ");

		return service.getAllTypes();
	}

	@GetMapping("/getcategorycount")
	public int getCountOfCategory() {
		System.out.println("counting categories in StylClos : ");

		return service.getCountOfCategory();
	}

	@GetMapping("/gettypecount")
	public int getCountOfType() {
		System.out.println("counting categories in StylClos : ");

		return service.getCountOfTypes();
	}

}
