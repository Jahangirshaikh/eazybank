package com.eazybytes.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eazybytes.model.Customer;
import com.eazybytes.repository.CustomerRepository;

@Service
public class UserService {
	
	private static CustomerRepository customerRepository;
	private static PasswordEncoder passwordEncoder;
	
	public UserService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
		UserService.customerRepository = customerRepository;
		UserService.passwordEncoder = passwordEncoder;
	}
	
	public Customer registerUser(Customer customer) {
		customer.setPwd(passwordEncoder.encode(customer.getPwd()));
		return customerRepository.save(customer);
	}
}
