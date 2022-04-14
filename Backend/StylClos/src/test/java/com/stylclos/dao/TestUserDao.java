package com.stylclos.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUserDao {

	@Autowired
	private UserRepository usrRepo;
	
	@Test
	void contextLoads() {
	}
	
	
	@Test
	void checkCount() {
		long count = usrRepo.countByRoleContaining("CUSTOMER");
		System.out.println(count);
		assertEquals(2, count);
	}
	
	
}
