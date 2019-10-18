package com.hcl.inglibrary.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.inglibrary.dto.UserResponseDto;
import com.hcl.inglibrary.entity.User;
import com.hcl.inglibrary.exception.UserNotFoundException;
import com.hcl.inglibrary.repository.UserRepository;
import com.hcl.inglibrary.util.ExceptionConstants;

/**
 * 
 * @author Manisha Yadav
 * @apiNote This class is used to fetch the user information who is registered
 *          with the library management system.
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	/*
	 * @Param -userId
	 * 
	 * @Response -UserResponseDto
	 * 
	 * @Exception -user not found
	 * 
	 * @Description -This method is used to fetch the details of the user which is
	 * available in the library.
	 */
	@Override
	public UserResponseDto fetchUserDetails(Integer userId) {

		UserResponseDto userResponseDto = new UserResponseDto();
		Optional<User> userDetials = userRepository.findById(userId);
		if (userDetials.isPresent()) {
			org.springframework.beans.BeanUtils.copyProperties(userDetials.get(), userResponseDto);
			return userResponseDto;
		} else {
			throw new UserNotFoundException(ExceptionConstants.USER_NOT_FOUND);
		}
	}

}
