package com.stylclos.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stylclos.pojos.Category;
import com.stylclos.pojos.Product;
import com.stylclos.pojos.Type;
import com.stylclos.pojos.User;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findAllByTitle(String title);
	
	List<Product> findAllByCategory(int cat);
	
	@Query("Select p from Product p")
	List<Product> getAllProducts();
	
	@Query("SELECT p from Product p where p.type.id=:id")
	List<Product> getProductsByTopicId(int id);
	
	@Query("SELECT p from Product p where p.seller.id=:id")
	List<Product> getProductsBySellerId(int id);
	
//	@Query("SELECT p from Product p join fetch p.size where p.id=:di")
//	Product getProduct(Integer di);
	
	@Query("SELECT c from Category c where c.id = ?1")
	Optional<Category> getCategory(Integer id);
	
	@Query("SELECT c from Category c")
	List<Category> getAllCategories();
	
	@Query("SELECT t from Type t where t.id = ?1")
	Optional<Type> getType(Integer id);
	
	@Query("SELECT t from Type t where t.cat.id=:id")
	List<Type> getAllTypeInCategory(int id);

	Product getByTitleAndSellerAndSize(String title, User seller, String size);
}
