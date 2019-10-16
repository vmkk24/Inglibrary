package com.hcl.inglibrary.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.inglibrary.dto.RegisterRequestDto;
import com.hcl.inglibrary.dto.RegisterResponseDto;
import com.hcl.inglibrary.entity.User;
import com.hcl.inglibrary.exception.CommonException;
import com.hcl.inglibrary.repository.UserRepository;
import com.hcl.inglibrary.util.ExceptionConstants;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	UserRepository userRepository;

	@Override
	public RegisterResponseDto register(RegisterRequestDto registerRequestDto) {
		Optional<User> checkRegisterForEmail = userRepository.findByEmail(registerRequestDto.getEmail());
		if (checkRegisterForEmail.isPresent()) {
			throw new CommonException(ExceptionConstants.EXIST_EMAIL);
		}
		if (registerRequestDto.getPassword().length() <= 6) {
			throw new CommonException(ExceptionConstants.INVALID_PASSWORD);
		}

		User user = new User();
		BeanUtils.copyProperties(registerRequestDto, user);
		user.setFailure(0);
		user.setLocker(false);
		User responseUser = userRepository.save(user);
		RegisterResponseDto registerResponseDto = new RegisterResponseDto();
		if (responseUser.getUserId() == null) {
			registerResponseDto.setMessage("registeration failure");
			registerResponseDto.setStatusCode(400);
		}
		registerResponseDto.setUserId(responseUser.getUserId());
		registerResponseDto.setMessage("registeration successfull");
		registerResponseDto.setStatusCode(200);
		return registerResponseDto;
	}

}
