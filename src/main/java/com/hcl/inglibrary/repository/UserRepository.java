package com.hcl.inglibrary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.inglibrary.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUserId(Integer userId);

	Optional<User> findByEmailAndPassword(String email, String password);

	Optional<User> findByEmailAndPasswordAndLocker(String email, String password, boolean locker);

	Optional<User> findByEmail(String email);

}
