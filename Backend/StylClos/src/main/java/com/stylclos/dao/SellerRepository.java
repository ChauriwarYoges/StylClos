package com.stylclos.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stylclos.pojos.Role;
import com.stylclos.pojos.User;

public interface SellerRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmailAndPasswordAndRole(String email, String password, Role role);
}
