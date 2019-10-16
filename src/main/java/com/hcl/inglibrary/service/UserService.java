package com.hcl.inglibrary.service;

import com.hcl.inglibrary.dto.UserResponseDto;

public interface UserService {

	UserResponseDto fetchUserDetails(Integer userId);

}
