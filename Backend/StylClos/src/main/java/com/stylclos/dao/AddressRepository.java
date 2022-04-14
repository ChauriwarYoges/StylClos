package com.stylclos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stylclos.pojos.Address;
import com.stylclos.pojos.User;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	@Query("SELECT a FROM Address a where a.user.id = :id")
	Address findAddressUsingUserId(int id);
	
	@Query("SELECT a FROM Address a where a.user = :id")
	Address findByFKUserId(int id);
	
}
