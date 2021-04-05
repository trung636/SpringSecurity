package com.spring.security.demo.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.security.demo.dto.UserDTO;
import com.spring.security.demo.entity.User;

@Component
public class UserMapper implements IMapping<User, UserDTO> {

	@Autowired
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public UserDTO toDTO(User t) {

		return modelMapper().map(t, UserDTO.class);
	}

	@Override
	public User toEntity(UserDTO d) {
		
		return modelMapper().map(d, User.class);
	}

}
