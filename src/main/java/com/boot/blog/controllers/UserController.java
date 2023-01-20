package com.boot.blog.controllers;

import java.util.List;

import org.apache.catalina.User;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.blog.entities.UserEntity;
import com.boot.blog.exceptions.ApiResponse;
import com.boot.blog.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	

	
	// Create User
	@PostMapping("/add_user")
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity us)
	{
		UserEntity newUser = this.userService.createUser(us);
		return new ResponseEntity<UserEntity>(newUser, HttpStatus.CREATED);
	}
	
	// get all users
	@GetMapping("/")
	public ResponseEntity<List<UserEntity>> allUsers()
	{
		List<UserEntity> getAllUsers = this.userService.getAllUser();
		
		return new ResponseEntity<List<UserEntity>>(getAllUsers, HttpStatus.OK); 
	}
	
	// get User By ID
	@GetMapping("/{userId}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable("userId") int userId )
	{
		UserEntity userById = this.userService.getUserById(userId);
		return new ResponseEntity<UserEntity>(userById, HttpStatus.OK);
	}
	
	
	// update user by ID
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserEntity> updateUserById(@RequestBody UserEntity us, @PathVariable("userId") int userId)
	{
		UserEntity updatedUser =this.userService.updateUser(us, userId);
		return new ResponseEntity<UserEntity>(updatedUser, HttpStatus.OK);
	}

	
	
	// delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") int userId)
	{
		this.userService.deleteUser(userId);
		ApiResponse response = new ApiResponse("User Deleted Success", true);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}
}
