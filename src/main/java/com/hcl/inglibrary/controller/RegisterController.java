package com.hcl.inglibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.inglibrary.dto.RegisterRequestDto;
import com.hcl.inglibrary.dto.RegisterResponseDto;
import com.hcl.inglibrary.exception.CommonException;
import com.hcl.inglibrary.service.RegisterService;
import com.hcl.inglibrary.util.ExceptionConstants;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/user")
@Slf4j
/**
 * 
 * @author SubhaMaheswaran
 *
 */
public class RegisterController {
	@Autowired
	RegisterService registerService;

	/*
	 * This method is used to register the user by providing valid details.
	 * 
	 * @Body RegisterRequestDto
	 * 
	 * @return RegisterResponseDto
	 * 
	 * @Exception INVALID_DETAILS will throw when the details are empty
	 */

	@PostMapping("/register")
	public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto) {
		log.info(":: Enter into RegisterController--------::register()");
		if (registerRequestDto.getUserName().isEmpty() || registerRequestDto.getEmail().isEmpty()
				|| registerRequestDto.getPassword().isEmpty() || registerRequestDto.getContact().isEmpty()) {
			throw new CommonException(ExceptionConstants.INVALID_DETAILS);
		}
		RegisterResponseDto registerResponseDto = registerService.register(registerRequestDto);
		return new ResponseEntity<>(registerResponseDto, HttpStatus.OK);
	}

}
