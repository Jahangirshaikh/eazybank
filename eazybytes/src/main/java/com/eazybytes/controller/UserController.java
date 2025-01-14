package com.eazybytes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.model.Customer;
import com.eazybytes.service.UserService;

@RestController
public class UserController {
	
	private static UserService userService;
	
	public UserController(UserService userService) {
		UserController.userService = userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Customer customer){
		Customer savedCustomer = userService.registerUser(customer);
		return new ResponseEntity<String>(savedCustomer.getEmail(), HttpStatus.ACCEPTED);
	}
	
}
