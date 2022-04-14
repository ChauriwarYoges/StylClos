package com.stylclos.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stylclos.custom_exception.ProductCategoryNotFoundException;
import com.stylclos.custom_exception.ProductTypeNotFoundException;
import com.stylclos.custom_exception.UserNotFoundException;
import com.stylclos.dao.CartRepository;
import com.stylclos.dao.CategoryRepository;
import com.stylclos.dao.ProductRepository;
import com.stylclos.dao.TypeRepository;
import com.stylclos.dao.UserRepository;
import com.stylclos.dto.AddProduct;
import com.stylclos.dto.ImageDTO;
import com.stylclos.dto.ProductResponseDTO;
import com.stylclos.dto.TypeResponseDTO;
import com.stylclos.dto.UpdateProductDTO;
import com.stylclos.pojos.Cart;
import com.stylclos.pojos.Category;
import com.stylclos.pojos.Product;
import com.stylclos.pojos.Type;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

	@Value("${image.upload.location}")
	private String location;

	@Autowired
	private ProductRepository proRepo;

	@Autowired
	private UserRepository usrRepo;

	@Autowired
	private CategoryRepository catRepo;

	@Autowired
	private TypeRepository typRepo;
	
	@Autowired
	private CartRepository crtRepo;

	public List<ProductResponseDTO> populateListWithData(List<Product> pros) {
		List<ProductResponseDTO> proResponse = new ArrayList<>();

		pros.forEach(pro -> {
			ProductResponseDTO prd = new ProductResponseDTO();

			// ASSIGN PRODUCT DATA TO PRODUCT-DTO
			prd.setId(pro.getId());
			prd.setTitle(pro.getTitle());
			prd.setImage(pro.getImage());
			prd.setCategory(pro.getCategory());
			prd.setType(pro.getType());
			prd.setQuantity(pro.getQauntity());
			prd.setPrice(pro.getPrice());
			prd.setSize(pro.getSize());
			prd.setSellerId(pro.getSeller().getId());

			// SET IMAGE DETAILS TO PRODUCT-DTO
			Path path = Paths.get(location, pro.getImage());

			prd.setName(pro.getImage());

			try {
				prd.setImgData(Files.readAllBytes(path));
				prd.setImgType(Files.probeContentType(path));
			} catch (IOException e) {

				System.out.println("Error occured while retreiving product id : " + pro.getId());
			}

			proResponse.add(prd);
		});

		return proResponse;
	}

	// Get complete Product list
	@Override
	public List<ProductResponseDTO> getAllProducts() throws IOException {
		// TODO Auto-generated method stub
		List<Product> p = proRepo.getAllProducts();

		List<ProductResponseDTO> proResponse = populateListWithData(p);

		return proResponse;
	}
	
	@Override
	public int getProductCount() {
		return (int)proRepo.count();
	}

	// Get list of Product according to category
	@Override
	public List<ProductResponseDTO> getProductsByCategory(String cat) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(cat);
		List<Product> pros = new ArrayList<>();
		proRepo.getAllProducts().stream().filter(s -> s.getCategory().getCategory().equalsIgnoreCase(cat))
				.forEach(s -> pros.add(s));
		
		if(pros.isEmpty())
			return null;

		List<ProductResponseDTO> proResponse = populateListWithData(pros);

		return proResponse;
	}

	/*
	 * SEARCH PRODUCT BY ID
	 */
	@Override
	public ProductResponseDTO getProductById(Integer id) throws IOException {
		// TODO Find product by it's ID
		Product pro = proRepo.findById(id)
				.orElseThrow(() -> new ProductCategoryNotFoundException("Product with id : " + id + " unavailable"));

		ProductResponseDTO prd = new ProductResponseDTO();

		// ASSIGN PRODUCT DATA TO PRODUCT-DTO
		prd.setId(pro.getId());
		prd.setTitle(pro.getTitle());
		prd.setImage(pro.getImage());
		prd.setCategory(pro.getCategory());
		prd.setType(pro.getType());
		prd.setQuantity(pro.getQauntity());
		prd.setPrice(pro.getPrice());
		prd.setSize(pro.getSize());
		prd.setSellerId(pro.getSeller().getId());

		// SET IMAGE DETAILS TO PRODUCT-DTO
		Path path = Paths.get(location, pro.getImage());
		prd.setName(pro.getImage());
		prd.setImgData(Files.readAllBytes(path));
		prd.setImgType(Files.probeContentType(path));

		return prd;
	}

	// INSERT NEW PRODUCT IN DB
	@Override
	public boolean addNewProduct(String productDetails, MultipartFile image) {
		// TODO : add new product with size to DB

		try {
			AddProduct pro = new ObjectMapper().readValue(productDetails, AddProduct.class);

			System.out.println("AddProduct : " + pro);

			Product product = new Product();
			product.setSeller(usrRepo.findByEmail(pro.getEmail()));

			Product pro1 = proRepo.getByTitleAndSellerAndSize(pro.getTitle(), product.getSeller(), pro.getSize());

			if (pro1 != null)
				throw new RuntimeException("Product already exists");

			product.setTitle(pro.getTitle());
			// SET IMAGE NAME IN DB
			product.setImage(image.getOriginalFilename());

			// SET CATEGORY & TYPE OF PRODUCT
			product.setCategory(
					proRepo.getCategory(pro.getCategory()).orElseThrow(() -> new ProductCategoryNotFoundException()));
			product.setType(proRepo.getType(pro.getType()).orElseThrow(() -> new ProductTypeNotFoundException()));

			// STORE IMAGE AT LOCATION
			image.transferTo(new File(location, image.getOriginalFilename()));

			product.setSize(pro.getSize());

			product.setQauntity(pro.getQty());

			product.setPrice(pro.getPrice());

			proRepo.save(product);

			return true;

		} catch (

		Exception e) {
			System.out.println("in catch exception");
			System.out.println(e.getMessage() + "\n" + e.getCause() + "\n" + e);
		}

		return false;
	}

	@Override
	public Category getCategory(int id) {
		// TODO get category
		Category c = proRepo.getCategory(id).orElseThrow(() -> new UserNotFoundException("Category Not Found"));
		System.out.println(c);
		return c;
	}

	@Override
	public TypeResponseDTO getType(int id) throws IOException {
		// TODO Auto-generated method stub
		Type t = proRepo.getType(id).orElseThrow(() -> new UserNotFoundException("Category Not Found"));
		
		TypeResponseDTO trd = new TypeResponseDTO();
		
		trd.setId(t.getId());
		trd.setTypeName(t.getTypeName());
		trd.setCategoryId(t.getCat().getId());
		
		Path path = Paths.get(location, t.getImage());
		trd.setName(t.getImage());
		trd.setImgData(Files.readAllBytes(path));
		trd.setImgType(Files.probeContentType(path));
		
//		System.out.println(t);
		return trd;
	}

	// DELETES PRODUCT FROM DB
	@Override
	public boolean removeProduct(int id) {
		Product p = proRepo.getById(id);

		try {
			
			File file = new File(location + p.getImage());
			
			List<Cart> carts = crtRepo.findByProductId(p.getId());
			if(file.exists()) {
				System.out.println("File exists : " + file.getAbsolutePath());
				file.delete();
			}
			crtRepo.deleteAll(carts);
			proRepo.delete(p);
			
			return true;
		} catch (Exception e) {
			System.out.println(" File not Found : " + e.getMessage());
		}
		
		return false;
	}

	// UPDATE PRODUCT IN DB
	@Override
	public boolean updateProduct(String prod, MultipartFile image) throws IOException {
		
		UpdateProductDTO product = new ObjectMapper().readValue(prod, UpdateProductDTO.class);
		
		Product p = proRepo.getById(product.getId());

		if (p == null)
			return false;

		System.out.println(p.getPrice());

		if (p.getCategory().getId() != product.getCategory())
			p.setCategory(catRepo.getById(product.getCategory()));

		if (p.getType().getId() != product.getId())
			p.setType(typRepo.getById(product.getType()));

		if (!p.getTitle().equals(product.getTitle()))
			p.setTitle(product.getTitle());

		if (!p.getSize().equals(product.getSize()))
			p.setSize(product.getSize());

		if (p.getQauntity() != product.getQty())
			p.setQauntity(product.getQty());

		if (p.getPrice() != product.getPrice())
			p.setPrice(product.getPrice());
		
		if (p.getImage() != image.getOriginalFilename()) {
			File file = new File(location + p.getImage());
			
			if(file.exists()) {
				file.delete();
				System.out.println("After deleting file : " + file.exists() + "\nFileAbsolute Path" +  file.getAbsolutePath());
			}
			
			image.transferTo(new File(location + image.getOriginalFilename()));
			
			p.setImage(image.getOriginalFilename());
			
		}

		proRepo.save(p);

		return true;
	}

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return proRepo.getAllCategories();
	}

	@Override
	public List<TypeResponseDTO> getAllTypes(int id) {
		// TODO Get Types in Category according to category id
		List<Type> types = proRepo.getAllTypeInCategory(id);
		
		List<TypeResponseDTO> trds = new ArrayList<>();
		
		types.forEach(type -> {
			TypeResponseDTO trd = new TypeResponseDTO();
			
			trd.setId(type.getId());
			trd.setTypeName(type.getTypeName());
			trd.setImage(type.getImage());
			
			// LOAD IMAGE DATA IN TYPE-DTO
			Path path = Paths.get(location, type.getImage());
			trd.setName(type.getImage());
			try {
			trd.setImgData(Files.readAllBytes(path));
			trd.setImgType(Files.probeContentType(path));
			}
			catch(IOException e) {
				System.out.println("Error while in retreiving Type id : " + type.getId());
			}
			
			trds.add(trd);
			
		});
		
		return trds;
	}

}
