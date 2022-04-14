package com.stylclos.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stylclos.custom_exception.PasswordNotMatchedException;
import com.stylclos.custom_exception.UserNotFoundException;
import com.stylclos.dao.AddressRepository;
import com.stylclos.dao.CartRepository;
import com.stylclos.dao.UserRepository;
import com.stylclos.dto.AddressDTO;
import com.stylclos.dto.CreateUserAccountRequest;
import com.stylclos.dto.LoginResponse;
import com.stylclos.dto.ProfileDTO;
import com.stylclos.pojos.Address;
import com.stylclos.pojos.Cart;
import com.stylclos.pojos.User;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AddressRepository addRepo;
	
	@Autowired
	private CartRepository crtRepo;

	// CHECK WHETHER USER IS VALID USER OR NOT
	@Override
	public LoginResponse authenticateUser(String email, String password, String role) {
		// TODO Auto-generated method stub
		User u = userRepo.findByEmailAndPasswordAndRole(email, password, role)
				.orElseThrow(() -> new UserNotFoundException(role + " entered invalid credential"));

		System.out.println(u.getRole());

		return new LoginResponse(u.getId(), u.getEmail(), u.getName(), u.getRole());
	}

	// ENROLL NEW USER ACCOUNT
	@Override
	public LoginResponse createNewAccount(@Valid CreateUserAccountRequest request) {
		if (request.getPassword().equals(request.getConfirmPassword())) {
			User cst;
			Set<String> role = new HashSet<>();
			role.add(request.getRole());
			if (request.getRegisterDate() == null)
				cst = new User(request.getEmail(), request.getName(), LocalDate.now(), request.getPassword(), role,
						request.getConfirmPassword());
			else
				cst = new User(request.getEmail(), request.getName(), LocalDate.parse(request.getRegisterDate()),
						request.getPassword(), role, request.getConfirmPassword());
			userRepo.save(cst);
			return new LoginResponse(cst.getId(), cst.getEmail(), cst.getName(), cst.getRole());
		} else
			throw new PasswordNotMatchedException("Re-type password donot matched");
	}

	// check whether user with email exists or not
	@Override
	public LoginResponse getUserUsingEmail(String email) {
		// TODO Auto-generated method stub
		User u = userRepo.findByEmail(email);
		
		System.out.println("check : " + (u == null));
		
		if(u == null)
			return null;
		
		System.out.println("email : " + u.getEmail());
		
		return new LoginResponse(u.getId(), u.getEmail(), u.getName(), u.getRole());
	}

	// add address to user
	@Override
	public boolean addAddress(AddressDTO address) {
		// TODO Auto-generated method stub
		User u = userRepo.getById(address.getUserId());
		Address existAddress = addRepo.findAddressUsingUserId(address.getUserId());
		Address add = new Address(address.getAreaName(), address.getCity(), address.getState(), address.getCountry());

		if (existAddress != null)
			throw new RuntimeException("Address already exist");

		if (u == null)
			return false;

		add.setUser(u);

		addRepo.save(add);

		return true;
	}

	// RETRIVE ADDRESS FROM DB OF USER ID : id
	@Override
	public Address getAddress(int id) {
		// TODO Auto-generated method stub
		return addRepo.findAddressUsingUserId(id);
	}

	// delete address from user
	@Override
	public boolean deleteAddress(int id) {
		// TODO Auto-generated method stub
		Address add = addRepo.findById(id).orElseThrow(() -> new RuntimeException("Address Not Found"));

		addRepo.delete(add);

		return true;
	}

	// DELETE USER FROM DATABASE, i.e. delete user account
	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		User u = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Invalid Id User Not Found"));

		Address add = addRepo.findAddressUsingUserId(id);
		
		if( add != null)
			addRepo.delete(add);
		
		List<Cart> carts = crtRepo.findByUserId(id);
		
		if(!carts.isEmpty())
			crtRepo.deleteAll(carts);
			
		userRepo.delete(u);

		return true;
	}

	@Override
	public boolean updateProfile(ProfileDTO profile) {
		// Updating user profile
		User u = userRepo.findById(profile.getUserId())
				.orElseThrow(() -> new UserNotFoundException("User not available"));

		if (!u.getName().equals(profile.getName())) {
			u.setName(profile.getName());
			userRepo.save(u);
		}

		try {
			Address add = getAddress(profile.getUserId());
			
			System.out.println();
			
			if(!add.getAreaName().equals(profile.getAreaName()))
				add.setAreaName(profile.getAreaName());

			if(!add.getCity().equals(profile.getCity()))
				add.setCity(profile.getCity());
			
			if(!add.getState().equals(profile.getState()))
				add.setState(profile.getState());
			
			if(!add.getCountry().equals(profile.getCountry()))
				add.setCountry(profile.getCountry());
			
			System.out.println("Reached here" + add.getCity());
			
			addRepo.save(add);
			
		} catch (RuntimeException e) {
			return addAddress(new AddressDTO(u.getId() , profile.getAreaName(), profile.getCity(), profile.getState(), profile.getCountry()));
		}	
		
		return true;
	}

}
