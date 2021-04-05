package com.spring.security.demo.service.imp;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.demo.dto.UserDTO;
import com.spring.security.demo.entity.Role;
import com.spring.security.demo.entity.User;
import com.spring.security.demo.mapping.UserMapper;
import com.spring.security.demo.repository.UserRepository;
import com.spring.security.demo.service.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper mapper;

	@Override
	public UserDTO save(UserDTO udto) {
		User user = mapper.toEntity(udto);
		User usave = userRepository.save(user);
		return mapper.toDTO(usave);
	}

	@Override
	public User findByEmail(String email) {

		return userRepository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
	}

}
