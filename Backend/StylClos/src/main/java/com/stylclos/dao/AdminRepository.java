package com.stylclos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stylclos.pojos.User;

public interface AdminRepository extends JpaRepository<User, Integer> {

}
