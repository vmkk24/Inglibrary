package com.hcl.inglibrary.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.inglibrary.dto.LoginRequest;
import com.hcl.inglibrary.dto.LoginResponse;
import com.hcl.inglibrary.exception.InvalidCredentialsException;
import com.hcl.inglibrary.service.LoginService;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

	@Mock
	LoginService loginService;

	@InjectMocks
	LoginController loginController;

	@Test
	public void testLogin() throws InvalidCredentialsException {
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setMessage("success");
		loginResponse.setStatusCode(200);
		loginResponse.setUserId(1);

		LoginRequest request = new LoginRequest();
		request.setEmail("sharathvemperala24@gmail.com");
		request.setPassword("123");
		Mockito.when(loginService.authenticate(Mockito.any())).thenReturn(loginResponse);
		ResponseEntity<LoginResponse> actual = loginController.login(request);
		assertNotNull(actual);

	}

}
