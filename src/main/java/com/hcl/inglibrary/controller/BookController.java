package com.hcl.inglibrary.controller;

import java.util.List;

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

import com.hcl.inglibrary.dto.BookListByUserResponseDto;
import com.hcl.inglibrary.dto.BookListResponseDto;
import com.hcl.inglibrary.dto.BookRequestDto;
import com.hcl.inglibrary.dto.DonateBookResponseDto;
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
		public ResponseEntity<List<BookListResponseDto>> getBooks(){
			log.info(":: Enter into BookController--------::getBooks()");
			return new ResponseEntity<List<BookListResponseDto>>(bookService.fetchBooks(),HttpStatus.OK);
		}
		
		@GetMapping("/{userId}")
		public ResponseEntity<List<BookListByUserResponseDto>> getBooksByUser(@PathVariable Integer userId){
			log.info(":: Enter into BookController--------::getBooksByUser()");
			return new ResponseEntity<List<BookListByUserResponseDto>>(bookService.fetchBooksByUser(userId),HttpStatus.OK);
		}
		
		@PostMapping("/book")
		public ResponseEntity<DonateBookResponseDto> donateBook(@RequestBody BookRequestDto bookRequestDto){
			log.info(":: Enter into BookController--------::donateBook()");
			return new ResponseEntity<>(bookService.donateBook(bookRequestDto),HttpStatus.OK);
		}
}
