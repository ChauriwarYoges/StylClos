package com.stylclos.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stylclos.pojos.User;

public interface CustomerRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmailAndPassword(String email, String password);
	
	Optional<User> findByEmail(String email);
	
}
