package com.spring.security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.spring.security.demo.dto.UserDTO;
import com.spring.security.demo.entity.User;

public interface UserService extends UserDetailsService {

	UserDTO save(UserDTO udto);
	
	User findByEmail(String email);
}
