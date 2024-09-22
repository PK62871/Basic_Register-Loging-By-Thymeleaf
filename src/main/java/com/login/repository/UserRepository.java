package com.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>{

	 User findByEmail(String email);
}
