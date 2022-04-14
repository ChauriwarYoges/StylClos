package com.stylclos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stylclos.pojos.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	@Query("SELECT c from Cart c where c.user.id=:id")
	List<Cart> findByUserId(int id);
	
	@Query("SELECT c from Cart c where c.product.id=:id")
	List<Cart> findByProductId(int id);
	
	@Query("select c from Cart c where c.product.id=:proId and c.user.id=:userId")
	Cart findByProIdAndUserId(int proId, int userId);

}
