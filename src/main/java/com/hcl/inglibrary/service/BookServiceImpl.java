package com.hcl.inglibrary.service;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.inglibrary.dto.BookListResponseDto;
import com.hcl.inglibrary.dto.RequestReserveDto;
import com.hcl.inglibrary.dto.ResponseReserveDto;
import com.hcl.inglibrary.entity.BookIssuedHistory;
import com.hcl.inglibrary.exception.CommonException;
import com.hcl.inglibrary.repository.BookIssuedHistoryRepository;
import com.hcl.inglibrary.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	BookIssuedHistoryRepository bookIssuedHistoryRepository;

	@Override
	public BookListResponseDto fetchBooks() {

		/*
		 * BookListResponseDto bookListResponseDto = new BookListResponseDto();
		 * List<Book> books = bookRepository.findAll(); if(books != null) {
		 * books.forEach(arg0); BeanUtils.copyProperties(books, bookListResponseDto);
		 * return bookListResponseDto; }else { throw new
		 * UserNotFoundException(ExceptionConstants.userNotFound); } }
		 */ return null;
	}

	@Override
	public ResponseReserveDto reserveBook(RequestReserveDto requestReserveDto) {
		if (requestReserveDto == null)
			throw new CommonException("");
		BookIssuedHistory bookIssuedHistory = new BookIssuedHistory();
		BeanUtils.copyProperties(requestReserveDto, bookIssuedHistory);
		bookIssuedHistory.setIssuedDate(LocalDate.now());
		bookIssuedHistory.setDueDate(LocalDate.now().plusDays(7));

		if (requestReserveDto.getStatus().equalsIgnoreCase("available"))
			bookIssuedHistory.setStatus("booked");

		else
			bookIssuedHistory.setStatus("prebooked");
		BookIssuedHistory responseBookIssuedHistoryRepository= bookIssuedHistoryRepository.save(bookIssuedHistory);
		return null;
	}

}
