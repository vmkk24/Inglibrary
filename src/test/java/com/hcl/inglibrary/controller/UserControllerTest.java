package com.hcl.inglibrary.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.inglibrary.dto.UserResponseDto;
import com.hcl.inglibrary.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@Mock
	UserService userService;
	@InjectMocks
	UserController userController;
	
	@Test
	public void testGetUser() {
		
		Integer userId = 1;
		if(userId!=null){
			UserResponseDto userResponseDto = new UserResponseDto();
			userResponseDto.setContact("124345553");
			userResponseDto.setEmail("mani@gmail.com");
			userResponseDto.setUserId(1);
			userResponseDto.setUserName("manisa");
			Mockito.when(userService.fetchUserDetails(Mockito.anyInt())).thenReturn(userResponseDto);
			ResponseEntity<UserResponseDto> actual = userController.getUser(userId);
			assertNotNull(actual);
			}
		
	}

}
