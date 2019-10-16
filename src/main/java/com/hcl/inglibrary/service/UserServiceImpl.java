package com.hcl.inglibrary.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.inglibrary.dto.UserResponseDto;
import com.hcl.inglibrary.entity.User;
import com.hcl.inglibrary.exception.UserNotFoundException;
import com.hcl.inglibrary.repository.UserRepository;
import com.hcl.inglibrary.util.ExceptionConstants;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserResponseDto fetchUserDetails(Integer userId) {

		UserResponseDto userResponseDto = new UserResponseDto();
		Optional<User> userDetials = userRepository.findById(userId);
		if(userDetials.isPresent()) {
		org.springframework.beans.BeanUtils.copyProperties(userDetials.get(), userResponseDto);
		return userResponseDto;
		}else {
			throw new UserNotFoundException(ExceptionConstants.userNotFound);
		}
	}

}
