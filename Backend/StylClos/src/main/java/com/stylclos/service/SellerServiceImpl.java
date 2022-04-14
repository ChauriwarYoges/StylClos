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

import com.stylclos.custom_exception.UserNotFoundException;
import com.stylclos.dao.AddressRepository;
import com.stylclos.dao.CartRepository;
import com.stylclos.dao.ProductRepository;
import com.stylclos.dao.SellerRepository;
import com.stylclos.dto.ProductResponseDTO;
import com.stylclos.pojos.Address;
import com.stylclos.pojos.Cart;
import com.stylclos.pojos.Product;
import com.stylclos.pojos.User;

@Service
@Transactional
public class SellerServiceImpl implements ISellerService {

	@Value("${image.upload.location}")
	private String location;

	@Autowired
	private CartRepository crtRepo;

	@Autowired
	private SellerRepository sellRepo;

	@Autowired
	private ProductRepository proRepo;

	@Autowired
	private AddressRepository addRepo;

	@Override
	public List<ProductResponseDTO> getAllProductBySellerId(int id) {
		// TODO Get Data of Products according to seller id
		List<Product> pros = proRepo.getProductsBySellerId(id);

		if (pros.isEmpty())
			return null;

		List<ProductResponseDTO> prds = new ArrayList<>();

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

			prds.add(prd);
		});

		return prds;
	}

	@Override
	public boolean deactivateSellerProfile(int id) {
		// DEACTIVATE SELLER PROFILE
		User u = sellRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Provide seller with id : " + id + " not available"));

		Address add = addRepo.findAddressUsingUserId(id);

		if (add != null)
			addRepo.delete(add);

		List<Product> products = proRepo.getProductsBySellerId(id);

		if (!products.isEmpty()) {
			products.forEach(product -> {
				List<Cart> carts = crtRepo.findByProductId(product.getId());
				crtRepo.deleteAll(carts);

				File file = new File(location + product.getImage());

				if (file.exists())
					file.delete();

			});

			proRepo.deleteAll(products);
		}

		sellRepo.delete(u);

		return true;
	}

}
