package com.boot.blog.services;

import java.util.List;

import com.boot.blog.entities.UserEntity;

public interface UserService {

	
	public UserEntity createUser(UserEntity us);
	
	public UserEntity updateUser(UserEntity us, int uid);
	
	public List<UserEntity> getAllUser();
	
	public UserEntity getUserById(int uid);
	
	void deleteUser(int uid);
	
	
}
