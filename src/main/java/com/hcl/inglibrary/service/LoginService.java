package com.hcl.inglibrary.service;

import com.hcl.inglibrary.dto.LoginRequest;
import com.hcl.inglibrary.dto.LoginResponse;
import com.hcl.inglibrary.dto.ResetPasswordRequest;
import com.hcl.inglibrary.dto.ResetPasswordResponse;
import com.hcl.inglibrary.entity.User;
import com.hcl.inglibrary.exception.InvalidCredentialsException;

public interface LoginService {
	
	public void updateLoginInfo(User users);
	
	//public String generatePassword();
	
	public LoginResponse authenticate(LoginRequest loginRequest) throws InvalidCredentialsException;

	public ResetPasswordResponse forgetPassword(ResetPasswordRequest resetPasswordRequest) ;

}