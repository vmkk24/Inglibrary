package com.hcl.inglibrary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.inglibrary.dto.LoginRequest;
import com.hcl.inglibrary.dto.LoginResponse;
import com.hcl.inglibrary.dto.ResetPasswordRequest;
import com.hcl.inglibrary.dto.ResetPasswordResponse;
import com.hcl.inglibrary.exception.InvalidCredentialsException;
import com.hcl.inglibrary.service.LoginService;

/**
 * 
 * @author sharath vemperala
 *
 */

@RestController
@CrossOrigin(allowedHeaders  = { "*", "*/" }, origins = { "*", "*/" })
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
		logger.info(":: Enter into LoginController--------::login()");

		return new ResponseEntity<LoginResponse>(loginService.authenticate(loginRequest), HttpStatus.CREATED);

	}

	@PutMapping("/forgottenpassword")
	public ResponseEntity<ResetPasswordResponse> forgottenPassword(
			@RequestBody ResetPasswordRequest resetPasswordRequest) throws InvalidCredentialsException {
		logger.info(":: Enter into LoginController--------::forgottenPassword()");

		return new ResponseEntity<ResetPasswordResponse>(loginService.forgetPassword(resetPasswordRequest),
				HttpStatus.CREATED);

	}

}
