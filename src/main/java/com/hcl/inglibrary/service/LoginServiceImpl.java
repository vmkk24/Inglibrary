package com.hcl.inglibrary.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.inglibrary.dto.LoginRequest;
import com.hcl.inglibrary.dto.LoginResponse;
import com.hcl.inglibrary.entity.User;
import com.hcl.inglibrary.exception.InvalidCredentialsException;
import com.hcl.inglibrary.repository.UserRepository;
import com.hcl.inglibrary.utils.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserRepository usersDao;

	/**
	 * 
	 * @param login credentials
	 * @return LoginResponse
	 * @throws InvalidCredentialsException
	 */

	@Override
	public LoginResponse authenticate(LoginRequest loginRequest) throws InvalidCredentialsException {

		/*
		 * This method is the service for logging into the application After 3
		 * unsuccessful attempts of logging into account, password will be reset and an
		 * email will be sent to the user to reset his password.
		 */

		log.info(":: Enter into LoginService--------::authenticate()");

		String message = "";
		LoginResponse loginResponse = new LoginResponse();
		Optional<User> user = usersDao.findByEmailAndPasswordAndLocker(loginRequest.getEmail(),
				loginRequest.getPassword(), false);

		if (!user.isPresent()) {
			Optional<User> failedUser = usersDao.findByEmail(loginRequest.getEmail());

			message = ApplicationConstants.INVALID_CREDENTIALS;

			if (failedUser.isPresent()) {
				log.info("Failure Attempts in controller : " + failedUser.get().getFailure());

				if (failedUser.get().getFailure() >= 3) {

					/*
					 * After 3 unsuccessful attempts of logging into account, password will be reset
					 * and an email will be sent to the user to reset his password.
					 */

					if (failedUser.get().getFailure() == 3) {
						Optional<User> updateuserO = usersDao.findByEmail(failedUser.get().getEmail());
						if (!(updateuserO.isPresent())) {
							throw new InvalidCredentialsException(message);
						}
						User updateuser = updateuserO.get();

						updateuser.setFailure(0);
						updateuser.setLocker(true);
						usersDao.save(updateuser);
					}
					message = ApplicationConstants.FAILED_ATTEMPTS_EXCEEDED;
					throw new InvalidCredentialsException(message);

				}

				else {
					User failedUserO = failedUser.get();
					failedUserO.setFailure(failedUserO.getFailure() + 1);
					usersDao.save(failedUserO);
					message = ApplicationConstants.PASSWOR_MISSMATCH;
					throw new InvalidCredentialsException(message);

				}

			} else {

				message = ApplicationConstants.USER_DOESNT_EXIST;
				throw new InvalidCredentialsException(message);

			}

		} else {
			message = ApplicationConstants.SUCCESS;
			loginResponse.setMessage(message);
			loginResponse.setStatusCode(HttpStatus.OK.value());

			if ((user.isPresent())) {
				loginResponse.setUserId(user.get().getUserId());
				return loginResponse;
			}
			throw new InvalidCredentialsException(message);

		}

	}

}
