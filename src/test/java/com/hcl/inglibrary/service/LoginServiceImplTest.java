package com.hcl.inglibrary.service;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.inglibrary.dto.LoginRequest;
import com.hcl.inglibrary.dto.LoginResponse;
import com.hcl.inglibrary.entity.User;
import com.hcl.inglibrary.exception.InvalidCredentialsException;
import com.hcl.inglibrary.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceImplTest {

	@Mock
	private UserRepository usersDao;
	@InjectMocks
	private LoginServiceImpl loginService;

	@Test
	public void testAuthenticate() throws InvalidCredentialsException {

		User user = new User();
		user.setAddress("banglore");

		user.setEmail("sharathvemperala24@gmail.com");
		user.setFailure(0);
		user.setPassword("1234");
		user.setUserId(1);
		user.setUserName("sharath");

		LoginRequest request = new LoginRequest();
		request.setEmail("sharathvemperala24@gmail.com");
		request.setPassword("1234");

		Optional<User> optionalUser = Optional.of(user);

		Mockito.when(usersDao.findByEmailAndPasswordAndLocker((Mockito.anyString()), (Mockito.anyString()),
				(Mockito.anyBoolean()))).thenReturn(optionalUser);
		LoginResponse response = loginService.authenticate(request);
		assertNotNull(response);

	}

	@Test(expected = InvalidCredentialsException.class)
	public void testAuthenticateWrongCredentials() throws InvalidCredentialsException {

		User user = new User();
		user.setAddress("banglore");
		user.setEmail("sharathvemperala24@gmail.com");
		user.setFailure(0);
		user.setPassword("1234");
		user.setUserId(1);
		user.setUserName("sharath");

		LoginRequest request = new LoginRequest();
		request.setEmail("sharathvemperala2@gmail.com");
		request.setPassword("123");

		Optional<User> optionalUser = Optional.of(user);

		Mockito.when(usersDao.findByEmailAndPasswordAndLocker((Mockito.anyString()), (Mockito.anyString()),
				(Mockito.anyBoolean()))).thenReturn(Optional.ofNullable(null));
		Mockito.when(usersDao.findByEmail(Mockito.anyString())).thenReturn(optionalUser);
		LoginResponse response = loginService.authenticate(request);
		assertNotNull(response);

	}

	@Test(expected = InvalidCredentialsException.class)
	public void testAuthenticateWrongPasswordCorrectEmail() throws InvalidCredentialsException {

		User user = new User();
		user.setAddress("banglore");

		user.setEmail("sharathvemperala24@gmail.com");
		user.setFailure(0);
		user.setPassword("1234");
		user.setUserId(1);
		user.setUserName("sharath");

		LoginRequest request = new LoginRequest();
		request.setEmail("sharathvemperala24@gmail.com");
		request.setPassword("123");

		Optional<User> optionalUser = Optional.of(user);

		Mockito.when(usersDao.findByEmailAndPasswordAndLocker((Mockito.anyString()), (Mockito.anyString()),
				(Mockito.anyBoolean()))).thenReturn(Optional.ofNullable(null));
		Mockito.when(usersDao.findByEmail(Mockito.anyString())).thenReturn(optionalUser);
		LoginResponse response = loginService.authenticate(request);
		assertNotNull(response);

	}

	@Test(expected = InvalidCredentialsException.class)
	public void testAuthenticateWrongCorrectEmailMorethanThreeTimes() throws InvalidCredentialsException {

		User user = new User();
		user.setAddress("banglore");
		user.setEmail("sharathvemperala24@gmail.com");
		user.setFailure(3);
		user.setPassword("1234");
		user.setUserId(1);
		user.setUserName("sharath");

		LoginRequest request = new LoginRequest();
		request.setEmail("sharathvemperala24@gmail.com");
		request.setPassword("123");

		Optional<User> optionalUser = Optional.of(user);

		Mockito.when(usersDao.findByEmailAndPasswordAndLocker((Mockito.anyString()), (Mockito.anyString()),
				(Mockito.anyBoolean()))).thenReturn(Optional.ofNullable(null));
		Mockito.when(usersDao.findByEmail(Mockito.anyString())).thenReturn(optionalUser);
		LoginResponse response = loginService.authenticate(request);
		assertNotNull(response);

	}

}
