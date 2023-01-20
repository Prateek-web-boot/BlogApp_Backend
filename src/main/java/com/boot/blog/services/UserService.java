package com.boot.blog.services;

import java.util.List;

import com.boot.blog.entities.UserEntity;

public interface UserService {

	
	UserEntity createUser(UserEntity us);
	
	UserEntity updateUser(UserEntity us, int uid);
	
	List<UserEntity> getAllUser();
	
	UserEntity getUserById(int uid);
	
	void deleteUser(int uid);
}
