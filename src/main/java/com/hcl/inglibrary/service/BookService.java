package com.hcl.inglibrary.service;

import java.util.List;

import com.hcl.inglibrary.dto.BookListResponseDto;

public interface BookService {

	List<BookListResponseDto> fetchBooks();

}
