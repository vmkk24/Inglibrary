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
import com.hcl.inglibrary.exception.NullInputException;
import com.hcl.inglibrary.service.UserService;
import com.hcl.inglibrary.util.ApplicationUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Manisha Yadav
 * @apiNote This controller is used to get the user details which is registered with the library management system. 
 */

	@RestController
	@RequestMapping("/users")
	@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
	@Slf4j
	public class UserController {

		@Autowired
		UserService userService;
		
		/*
		 * @Param -userId
		 * @Response -ResponseEntity of UserResponseDto
		 * @Exception -user not found
		 * @Description -This method is used to fetch the details of the user which is available in the library.
		 * */
		@GetMapping("/{userId}")
		public ResponseEntity<UserResponseDto> getUser(@PathVariable Integer userId){
			log.info(":: Enter into UserController--------::getUser()");
			if(userId!=null){
			return new ResponseEntity<UserResponseDto>(userService.fetchUserDetails(userId),HttpStatus.OK);
			}else{
				throw new NullInputException(ApplicationUtil.UserNull);
			}
		}
}
