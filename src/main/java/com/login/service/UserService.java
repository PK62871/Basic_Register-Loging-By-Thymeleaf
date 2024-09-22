package com.login.service;

import com.login.entity.User;

public interface UserService {

	public boolean registerUser(User user);
	
	public User loginUser(String email,String password);
	
	
}
