package com.hcl.inglibrary.service;

import com.hcl.inglibrary.dto.LoginRequest;

import com.hcl.inglibrary.dto.LoginResponse;
import com.hcl.inglibrary.exception.InvalidCredentialsException;

public interface LoginService {

	public LoginResponse authenticate(LoginRequest loginRequest) throws InvalidCredentialsException;

}