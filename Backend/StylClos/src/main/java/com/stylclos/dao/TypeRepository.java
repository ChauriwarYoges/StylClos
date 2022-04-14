package com.stylclos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stylclos.pojos.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {

	Type findByTypeName(String name);
	
	@Query("SELECT t from Type t where t.cat.id=:id")
	List<Type> getTypesByCategoryId(int id);
	
	@Query("select t from Type t")
	List<Type> getAllTypesinDB();
	
//	@Query("SELECT t FROM Type t join fetch t.subType where t.id= ?1")
//	Type getCompleteType(Integer di);
	
}
