package com.stylclos.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stylclos.custom_exception.ProductCategoryNotFoundException;
import com.stylclos.custom_exception.ResourceNotFoundException;
import com.stylclos.custom_exception.UserNotFoundException;
import com.stylclos.dao.CartRepository;
import com.stylclos.dao.ProductRepository;
import com.stylclos.dao.UserRepository;
import com.stylclos.dto.AddToCart;
import com.stylclos.dto.CartResponseDTO;
import com.stylclos.pojos.Cart;
import com.stylclos.pojos.Product;
import com.stylclos.pojos.User;

@Service
@Transactional
public class CartServiceImpl implements ICartService {

	@Value("${image.upload.location}")
	private String location;

	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private UserRepository usrRepo;

	@Autowired
	private ProductRepository proRepo;

	@Override
	public List<CartResponseDTO> getAllCartItems(int id) {
		// TODO Auto-generated method stub
		List<Cart> carts = cartRepo.findByUserId(id);

		if (carts.isEmpty())
			return null;

		List<CartResponseDTO> cartResponse = new ArrayList<>();

		carts.forEach(c -> {

			CartResponseDTO crd = new CartResponseDTO();

			crd.setId(c.getId());
			crd.setTitle(c.getProduct().getTitle());
			crd.setImage(c.getProduct().getImage());
			crd.setCategory(c.getProduct().getCategory());
			crd.setType(c.getProduct().getType());
			crd.setPrice(c.getProduct().getPrice());
			crd.setQuantity(c.getQty());
			crd.setSize(c.getSize());
			crd.setSellerId(c.getProduct().getSeller().getId());

			// SET UP IMAGE FOR CART ITEMS
			Path path = Paths.get(location, c.getProduct().getImage());
			crd.setName(c.getProduct().getImage());

			try {
				crd.setImgData(Files.readAllBytes(path));
				crd.setImgType(Files.probeContentType(path));
			} catch (Exception e) {
				System.out.println("Error while getting Image for cart : ");
			}

			cartResponse.add(crd);

		});

		return cartResponse;
	}

	/*
	 * FIND CART BY PRODUCT ID AND USER ID
	 */
	@Override
	public boolean checkItemInCart(int proId, int userId) {
		// TODO Auto-generated method stub

		Cart c = cartRepo.findByProIdAndUserId(proId, userId);

		if (c == null)
			return false;

		return true;
	}

	/*
	 * ADD ITEM TO CART
	 */
	@Override
	public boolean AddItemToCart(AddToCart item) {
		// TODO Auto-generated method stub

		User u = usrRepo.findById(item.getUserId()).orElseThrow(() -> new UserNotFoundException("Invalid user"));

		Product p = proRepo.findById(item.getProId())
				.orElseThrow(() -> new ProductCategoryNotFoundException("Invalid product Id, Product Not Found"));

//		Cart checkCart = cartRepo.findCartItemByProductId(item.getProId());
//
//		if (checkCart != null)
//			return false;

		Cart c = new Cart(item.getQty(), item.getSize(), p, u);

		cartRepo.save(c);

		return true;
	}

	@Override
	public boolean deleteCartItem(int id) {
		// TODO Auto-generated method stub

		Cart c = cartRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart not found with id " + id));

		cartRepo.delete(c);

		return true;
	}

	@Override
	public boolean updateCartQty(int id, int value) {
		// update cart quantity

		Cart c = cartRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart not found with id " + id));
		Product p = proRepo.findById(c.getProduct().getId()).orElseThrow(
				() -> new ResourceNotFoundException("Product not found with id : " + c.getProduct().getId()));

		int qty = c.getQty() + value;

		if (qty > p.getQauntity()) {
			System.out.println("Cannot exceed cart quantity as stock is limited");
			return false;
		}

		if (qty < 1) {
			System.out.println("Cart must have 1 quantity atleast");
			return false;
		}

		c.setQty(c.getQty() + value);

		cartRepo.save(c);

		return true;
	}

	@Override
	public int getCartItemCount(int id) {
		// Get total cart items user has
		List<Cart> items = cartRepo.findByUserId(id);

		return items.size();
	}

}
