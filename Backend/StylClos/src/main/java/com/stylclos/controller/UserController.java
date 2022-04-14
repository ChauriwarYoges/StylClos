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

import com.stylclos.dao.UserRepository;
import com.stylclos.dto.AddressDTO;
import com.stylclos.dto.CreateUserAccountRequest;
import com.stylclos.dto.LoginRequest;
import com.stylclos.dto.LoginResponse;
import com.stylclos.dto.ProfileDTO;
import com.stylclos.pojos.Address;
import com.stylclos.pojos.User;
import com.stylclos.service.IUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
	@Autowired
	private IUserService service;
	
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("/signin")
	public ResponseEntity<?> loginCustomer(@RequestBody LoginRequest request) {
		System.out.println("in user get signin : " + request);
		return new ResponseEntity<>(service.authenticateUser(request.getEmail(), request.getPassword(), request.getRole()), HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> createNewCustomerAccount(@RequestBody @Valid CreateUserAccountRequest request) {
		System.out.println("In create user account : " + request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.createNewAccount(request));
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<?> getCustomerUsingEmail(@PathVariable String email) {
		System.out.println("in getCustomerUsingEmail ");
		LoginResponse lr = service.getUserUsingEmail(email);
		if(lr == null)
			return null;
		return ResponseEntity.ok(lr);
	}
	
	@GetMapping("/address/{id}")
	public ResponseEntity<?> getAddressOfUser(@PathVariable int id) {
		System.out.println("in get Address ");
		Address add = service.getAddress(id);
		System.out.println(add.getAreaName());
		return new ResponseEntity<>(add, HttpStatus.OK);
	}
	
	@PostMapping("/address")
	public ResponseEntity<?> setAddress(@RequestBody @Valid AddressDTO add) {
		System.out.println("in setAddress() ");
		
		return new ResponseEntity<>(service.addAddress(add), HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping("/updateprofile")
	public boolean updateProfile(@RequestBody @Valid ProfileDTO profile) {
		System.out.println("in update profile");
		return service.updateProfile(profile);
	}
	
	@GetMapping("/removeaddress/{id}")
	public boolean removeAddress(@PathVariable int id) {
		System.out.println("in remove address : " + id);
		return service.deleteAddress(id);
	}
	
	@GetMapping("/deactivate/{id}")
	public boolean deleteUser(@PathVariable int id) {
		System.out.println("in delete User : " + id);
		
		return service.deleteUser(id);
	}
	
	
	@GetMapping("/allusers")
	public ResponseEntity<?> allUser() {
		System.out.println("get all users present in db : ");
		
		List<User> users = userRepo.findAll();
		
		System.out.println(users != null);
		
//		users.
		
		if(users != null)
		users.forEach(user -> System.out.println(user.getRole().size()));
		
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

}
