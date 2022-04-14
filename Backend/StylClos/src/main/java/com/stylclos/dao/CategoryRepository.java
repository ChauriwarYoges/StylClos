package com.stylclos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stylclos.pojos.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	Category findByCategory(String cat);

}
