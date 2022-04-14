package com.stylclos.service;

import java.util.List;

import com.stylclos.dto.AddToCart;
import com.stylclos.dto.CartResponseDTO;

public interface ICartService {
	List<CartResponseDTO> getAllCartItems(int id);

	boolean AddItemToCart(AddToCart item);

	boolean deleteCartItem(int id);

	boolean updateCartQty(int id, int value);
	
	int getCartItemCount(int id);
	
	boolean checkItemInCart(int proId, int userId);
}
