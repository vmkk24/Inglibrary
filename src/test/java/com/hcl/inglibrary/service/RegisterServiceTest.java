package com.hcl.inglibrary.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.inglibrary.dto.RegisterRequestDto;
import com.hcl.inglibrary.dto.RegisterResponseDto;
import com.hcl.inglibrary.entity.User;
import com.hcl.inglibrary.exception.CommonException;
import com.hcl.inglibrary.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class RegisterServiceTest {

	@Mock
	UserRepository userRepository;

	@InjectMocks
	RegisterServiceImpl registerServiceImpl;

	RegisterRequestDto registerRequestDto;

	RegisterRequestDto registerRequestDt1o;

	User user;
	User user1;

	@Before
	public void setup() {
		registerRequestDto = new RegisterRequestDto();
		registerRequestDto.setUserName("subha");
		registerRequestDto.setPassword("subha1");
		registerRequestDto.setEmail("subhamahesh@gmail.com");

		user = new User();
		user.setUserId(1);
		user.setUserName("subha");
		user.setPassword("subha1");

		registerRequestDt1o = new RegisterRequestDto();
		registerRequestDt1o.setPassword("12");

		registerRequestDt1o.setUserName("subha");
		registerRequestDt1o.setEmail("subhamahesh1@gmail.com");

	}

	@Test
	public void testRegister() {
		Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.ofNullable(null));
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

		RegisterResponseDto registerResponseDto = registerServiceImpl.register(registerRequestDto);
		Assert.assertEquals(Integer.valueOf(1), registerResponseDto.getUserId());

	}

	@Test(expected = CommonException.class)
	public void testRegisterNegative() {
		Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));

		RegisterResponseDto registerResponseDto = registerServiceImpl.register(registerRequestDto);
		Assert.assertEquals(Integer.valueOf(1), registerResponseDto.getUserId());

	}

	@Test(expected = CommonException.class)
	public void testRegisterNegativePassword() {

		RegisterResponseDto registerResponseDto = registerServiceImpl.register(registerRequestDt1o);
		Assert.assertEquals(Integer.valueOf(1), registerResponseDto.getUserId());

	}
}
