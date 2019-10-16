package com.hcl.inglibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.inglibrary.dto.UserResponseDto;
import com.hcl.inglibrary.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
	 * 
	 * @author Manisha Yadav
	 *
	 */

	@RestController
	@RequestMapping("/users")
	@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
	@Slf4j
	public class UserController {

		@Autowired
		UserService userService;
		
		@GetMapping("/{userId}")
		public ResponseEntity<UserResponseDto> getUser(@PathVariable Integer userId){
			log.info(":: Enter into UserController--------::getUser()");
			return new ResponseEntity<UserResponseDto>(userService.fetchUserDetails(userId),HttpStatus.OK);
			
		}
}
