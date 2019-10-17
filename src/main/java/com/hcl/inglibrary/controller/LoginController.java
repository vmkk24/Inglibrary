package com.hcl.inglibrary.controller;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.inglibrary.dto.LoginRequest;
import com.hcl.inglibrary.dto.LoginResponse;
import com.hcl.inglibrary.exception.InvalidCredentialsException;
import com.hcl.inglibrary.service.LoginService;
import com.hcl.inglibrary.utils.ApplicationConstants;

/**
 * 
 * @author sharath vemperala
 *
 */

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	LoginService loginService;

	/**
	 * 
	 * @param login credentials
	 * @return LoginResponse
	 * @throws InvalidCredentialsException
	 */

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest)
			throws InvalidCredentialsException {

		/*
		 * This method is the service for logging into the application After 3
		 * unsuccessful attempts of logging into account, password will be reset and an
		 * email will be sent to the user to reset his password.
		 */
		logger.info(":: Enter into LoginController--------::login()");
		if(loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
			String message = ApplicationConstants.INVALID_CREDENTIALS;
			throw new InvalidCredentialsException(message);
		}

		return new ResponseEntity<LoginResponse>(loginService.authenticate(loginRequest), HttpStatus.CREATED);

	}

}
