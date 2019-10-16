package com.hcl.inglibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.inglibrary.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);

}
