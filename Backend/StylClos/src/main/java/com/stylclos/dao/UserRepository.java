package com.stylclos.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stylclos.pojos.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmailAndPasswordAndRole(String email, String pass, String role);
	
	User findByEmail(String email);
	
	@Query("select u from User u")
	List<User> getAllUsers();
	
	@Query(nativeQuery=true, value="select count(u.id) from User u where :roleName in (select role1_.role from rolestab role1_ where u.id=role1_.id)")
	long countByRoleContaining(String roleName);
	
}
