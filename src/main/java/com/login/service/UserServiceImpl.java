package com.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.entity.User;
import com.login.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean registerUser(User user) {
		
		try {
			userRepository.save(user);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User loginUser(String email, String password) {
		User user = userRepository.findByEmail(email);
		
		if(user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

	
}
