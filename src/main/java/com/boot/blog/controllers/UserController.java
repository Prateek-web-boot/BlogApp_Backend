package com.boot.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.blog.entities.UserEntity;
import com.boot.blog.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	

	
	// Create User
	@PostMapping("/")
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity us)
	{
		UserEntity newUser = this.userService.createUser(us);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
}
