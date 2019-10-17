package com.hcl.inglibrary.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.inglibrary.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	Optional<List<Book>> findByStatus(String status);

}
