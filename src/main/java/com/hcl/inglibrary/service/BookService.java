package com.hcl.inglibrary.service;

import java.util.List;

import com.hcl.inglibrary.dto.BookListByUserResponseDto;
import com.hcl.inglibrary.dto.BookListResponseDto;
import com.hcl.inglibrary.dto.BookRequestDto;
import com.hcl.inglibrary.dto.DonateBookResponseDto;

public interface BookService {

	List<BookListResponseDto> fetchBooks();

	DonateBookResponseDto donateBook(BookRequestDto bookRequestDto);

	List<BookListByUserResponseDto> fetchBooksByUser(Integer userId);

}
