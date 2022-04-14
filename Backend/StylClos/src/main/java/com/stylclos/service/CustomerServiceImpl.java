package com.stylclos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stylclos.dao.CustomerRepository;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {
	
	@Autowired
	private CustomerRepository custRepo;

	
}
