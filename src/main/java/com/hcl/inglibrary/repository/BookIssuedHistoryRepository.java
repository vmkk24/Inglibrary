package com.hcl.inglibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.inglibrary.entity.BookIssuedHistory;

public interface BookIssuedHistoryRepository extends JpaRepository<BookIssuedHistory, Integer> {

	BookIssuedHistory findByBookIdAndStatus(Integer bookId, String string);

	List<BookIssuedHistory> findAllByUserId(Integer userId);

	List<BookIssuedHistory> findAllByUserIdAndStatus(Integer userId, String string);
}
