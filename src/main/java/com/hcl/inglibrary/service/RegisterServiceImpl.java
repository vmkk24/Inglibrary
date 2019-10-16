package com.hcl.inglibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.inglibrary.dto.RegisterRequestDto;
import com.hcl.inglibrary.dto.RegisterResponseDto;
import com.hcl.inglibrary.repository.UserRepository;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	UserRepository userRepository;

	@Override
	public RegisterResponseDto register(RegisterRequestDto registerRequestDto) {

		return null;
	}

}
