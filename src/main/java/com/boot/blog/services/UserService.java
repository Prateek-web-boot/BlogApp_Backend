package com.boot.blog.services;

import java.util.List;

import com.boot.blog.entities.User;

public interface UserService {

	
	User createUser(User us);
	
	User updateUser(User us, int uid);
	
	List<User> getAllUser();
	
	User getUserById(int uid);
	
	void deleteUser(int uid);
}
