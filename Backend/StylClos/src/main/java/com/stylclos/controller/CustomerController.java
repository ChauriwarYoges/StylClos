package com.stylclos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stylclos.dto.AddToCart;
import com.stylclos.dto.CartResponseDTO;
import com.stylclos.dto.CartUpdateDTO;
import com.stylclos.service.ICartService;
import com.stylclos.service.IUserService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

	@Autowired
	private IUserService service;
	
	@Autowired
	private ICartService cartService;
	
	@GetMapping("/cart/{id}")
	public ResponseEntity<?> getCartItemsOfUser(@PathVariable int id) {
		System.out.println("in getCart Items");
		
		List<CartResponseDTO> carts = cartService.getAllCartItems(id);
		
		carts.forEach(c -> System.out.println(c.getId()));
		
		return new ResponseEntity<>(carts, HttpStatus.OK);
	}
	
	@PostMapping("/addtocart")
	public boolean addItemsToCart(@RequestBody @Valid AddToCart addItem) {
		System.out.println("add to cart item  : " + addItem.getProId());
		return cartService.AddItemToCart(addItem) ;
	}
	
	@GetMapping("/checkcart/{proId}/{userId}")
	public boolean checkItemExistsInCartAlready(@PathVariable int proId, @PathVariable int userId) {
		System.out.println("check product exists in cart of ProductId & userId  : " + proId + " " + userId);
		return cartService.checkItemInCart(proId, userId);
	}
	
	@GetMapping("/deletecart/{id}")
	public ResponseEntity<?> deleteItemOfCart(@PathVariable int id) {
		System.out.println("in delete cart item : " + id);
		
		return ResponseEntity.status(HttpStatus.OK).body(cartService.deleteCartItem(id));
	}
	
	@PostMapping("/updateCartQty")
	public ResponseEntity<?> updateCartQty(@RequestBody CartUpdateDTO cud) {
		System.out.println("in cart quantity update : ");
		
		return ResponseEntity.status(HttpStatus.OK).body(cartService.updateCartQty(cud.getId(), cud.getValue()));
		
	}
	
	@GetMapping("/cartcount/{id}")
	public ResponseEntity<?> getCartItemCount(@PathVariable int id) {
		System.out.println("count cart items of customer id : " + id);
		
		return ResponseEntity.status(HttpStatus.OK).body(cartService.getCartItemCount(id));
	}
	
}
