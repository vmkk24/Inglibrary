package com.hcl.inglibrary.service;

import com.hcl.inglibrary.dto.BookListResponseDto;
import com.hcl.inglibrary.dto.RequestReserveDto;
import com.hcl.inglibrary.dto.ResponseReserveDto;

public interface BookService {

	BookListResponseDto fetchBooks();

	ResponseReserveDto reserveBook(RequestReserveDto requestReserveDto);

}
