package com.boot.blog.serviceImplemetation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boot.blog.entities.User;
import com.boot.blog.repositories.UserRepository;
import com.boot.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	UserRepository userRepo;

	@Override
	public User createUser(User us) {
		// TODO Auto-generated method stub
		return this.userRepo.save(us);
	
	}

	@Override
	public User updateUser(User us, int uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(int uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(int uid) {
		// TODO Auto-generated method stub

	}

}
