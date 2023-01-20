package com.boot.blog.serviceImplemetation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.blog.entities.UserEntity;
import com.boot.blog.exceptions.ResourceNotFoundException;
import com.boot.blog.repositories.UserRepository;
import com.boot.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserEntity createUser(UserEntity us) {
		// TODO Auto-generated method stub
		return this.userRepo.save(us);
	
	}

	@Override
	public UserEntity updateUser(UserEntity us, int uid) {
	
		
		if(!this.userRepo.existsById(uid))
		{
			throw new ResourceNotFoundException("User", "User Id", uid);
		}
		else
		{
			return this.userRepo.save(us);
		}
		
	}
	


	@Override
	public List<UserEntity> getAllUser() {
	
		return this.userRepo.findAll();
	}

	@Override
	public UserEntity getUserById(int uid) {
	
		return this.userRepo.findById(uid).orElseThrow(() -> new ResourceNotFoundException("User", "id", uid));
	}

	@Override
	public void deleteUser(int uid) {
		// TODO Auto-generated method stub
		
		if(!this.userRepo.existsById(uid))
		{
			throw new ResourceNotFoundException("User", "User Id", uid);
		}
		else
		{
			this.userRepo.deleteById(uid);
		}

	}

}
