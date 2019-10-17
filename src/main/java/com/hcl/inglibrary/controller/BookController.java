package com.hcl.inglibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		return new ResponseEntity<>(bookService.fetchBooks(), HttpStatus.OK);
	}

	/*
	 * This method is used to reserve or preReserve the book.
	 * 
	 * @Body RequestReserveDto
	 * 
	 * @return ResponseReserveDto
	 * 
	 */
	@PostMapping("books/{bookId}")
	public ResponseEntity<ResponseReserveDto> reserveBook(@RequestBody RequestReserveDto requestReserveDto,
			@PathVariable("bookId") Integer bookId) {
		log.info(":: Enter into BookController--------::reserveBooks()");
		ResponseReserveDto responseReserveDto = bookService.reserveBook(requestReserveDto, bookId);
		return new ResponseEntity<>(responseReserveDto, HttpStatus.OK);
	}

}
