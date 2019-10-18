package com.hcl.inglibrary.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.inglibrary.dto.UserResponseDto;
import com.hcl.inglibrary.entity.User;
import com.hcl.inglibrary.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@Mock
	UserRepository userRepository;
	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Test
	public void testFetchUserDetails() {

		User user = new User();
		user.setEmail("manisha@gmail.com");
		user.setFailure(0);
		user.setLocker(false);
		user.setPassword("875fyf");
		user.setUserId(1);
		user.setUserName("manisha");
		user.setAddress("bangalore");

		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));

		UserResponseDto actual = userServiceImpl.fetchUserDetails(1);
		Assert.assertEquals(Integer.valueOf(1), actual.getUserId());

	}

}
