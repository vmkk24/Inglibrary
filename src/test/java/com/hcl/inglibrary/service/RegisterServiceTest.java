//package com.hcl.inglibrary.service;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import com.hcl.inglibrary.dto.RegisterRequestDto;
//import com.hcl.inglibrary.dto.RegisterResponseDto;
//import com.hcl.inglibrary.entity.User;
//import com.hcl.inglibrary.repository.UserRepository;
//
//@RunWith(MockitoJUnitRunner.class)
//public class RegisterServiceTest {
//
//	@Mock
//	UserRepository userRepository;
//
//	@InjectMocks
//	RegisterServiceImpl registerServiceImpl;
//
//	RegisterRequestDto registerRequestDto;
//
//	User user;
//
//	@Before
//	public void setup() {
//		registerRequestDto = new RegisterRequestDto();
//		registerRequestDto.setUserName("subha");
//
//		registerRequestDto.setEmail("subha@gmail.com");
//
//		user = new User();
//		user.setUserId(1);
//		user.setUserName("subha");
//
//		user.setContact("9988776655");
//		user.setPassword("KoMcw");
//
//	}
//
//	@Test
//	public void testRegister() {
//		Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(null);
//		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
//
//		RegisterResponseDto registerResponseDto = registerServiceImpl.register(registerRequestDto);
//		Assert.assertEquals("1", registerResponseDto.getUserId());
//
//	}
//}
