package com.hcl.inglibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.inglibrary.dto.BookListResponseDto;
import com.hcl.inglibrary.dto.RequestReserveDto;
import com.hcl.inglibrary.dto.ResponseReserveDto;
import com.hcl.inglibrary.service.BookService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Manisha Yadav
 *
 */

@RestController
@RequestMapping("/books")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@Slf4j
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("/")
	public ResponseEntity<BookListResponseDto> getBooks() {
		log.info(":: Enter into BookController--------::getBooks()");
		return new ResponseEntity<BookListResponseDto>(bookService.fetchBooks(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ResponseReserveDto> reserveBook(RequestReserveDto requestReserveDto) {
		ResponseReserveDto responseReserveDto = bookService.reserveBook(requestReserveDto);
		return null;
	}

}
