package com.stylclos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stylclos.service.ISellerService;

@RestController
@RequestMapping("/seller")
@CrossOrigin(origins = "http://localhost:3000")
public class SellerController {
	
	@Autowired
	private ISellerService service;
	
	@GetMapping("/getproducts/{id}")
	public ResponseEntity<?> getAllProductsOfSeller(@PathVariable int id) {
		System.out.println("in get ALL products of seller id : " + id);
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllProductBySellerId(id));
	}

	@GetMapping("/deactivateseller/{id}")
	public ResponseEntity<?> deactivateSellerProfile(@PathVariable int id) {
		System.out.println("In deactivate seller profile : " + id);
		return ResponseEntity.status(HttpStatus.OK).body(service.deactivateSellerProfile(id));
	}
}
