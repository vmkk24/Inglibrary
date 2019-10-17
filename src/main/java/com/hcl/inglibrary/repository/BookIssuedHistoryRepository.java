package com.hcl.inglibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.inglibrary.entity.BookIssuedHistory;

public interface BookIssuedHistoryRepository extends JpaRepository<BookIssuedHistory, Integer> {

	BookIssuedHistory findByBookIdAndStatus(Integer bookId, String string);

}
