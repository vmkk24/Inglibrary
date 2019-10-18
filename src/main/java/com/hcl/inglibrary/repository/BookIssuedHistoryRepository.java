package com.hcl.inglibrary.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.inglibrary.entity.BookIssuedHistory;

public interface BookIssuedHistoryRepository extends JpaRepository<BookIssuedHistory, Integer> {

	Optional<List<BookIssuedHistory>> findByDueDate(LocalDate dueDate);

	Optional<List<BookIssuedHistory>> findByDueDateAndStatus(LocalDate dueDate, String status);

	BookIssuedHistory findByBookIdAndStatus(Integer bookId, String string);

	List<BookIssuedHistory> findAllByUserIdAndStatus(Integer userId, String string);

}
