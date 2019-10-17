package com.hcl.inglibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.inglibrary.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	Book findByBookId(Integer bookId);

	List<Book> findByUserId(Integer userId);

}
