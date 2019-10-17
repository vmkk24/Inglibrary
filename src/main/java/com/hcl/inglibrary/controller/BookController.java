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
import com.hcl.inglibrary.exception.NullInputException;
import com.hcl.inglibrary.service.BookService;
import com.hcl.inglibrary.util.ApplicationUtil;

import lombok.extern.slf4j.Slf4j;

	/**
	 * 
	 * @author Manisha Yadav
	 * @apiNote This controller is used to get/save the books from/to our library management system. 
	 */

	@RestController
	@RequestMapping("/books")
	@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
	@Slf4j
	public class BookController {

		@Autowired
		BookService bookService;
		
		/*
		 * @Param -no param
		 * @Response -ResponseEntity of list of books
		 * @Description -This method is used to fetch all the list of books which is available in the library.
		 * */
		@GetMapping("/")
		public ResponseEntity<List<BookListResponseDto>> getBooks(){
			log.info(":: Enter into BookController--------::getBooks()");
			return new ResponseEntity<List<BookListResponseDto>>(bookService.fetchBooks(),HttpStatus.OK);
		}
		
		/*
		 * @Param -userId
		 * @Response -ResponseEntity of list of books
		 * @Exception -userId should not be null.
		 * @Description -This method is used to fetch all the list of books which is issued to the specific user.
		 * */
		@GetMapping("/{userId}")
		public ResponseEntity<List<BookListByUserResponseDto>> getBooksByUser(@PathVariable Integer userId){
			log.info(":: Enter into BookController--------::getBooksByUser()");
			if(userId != null) {
			return new ResponseEntity<List<BookListByUserResponseDto>>(bookService.fetchBooksByUser(userId),HttpStatus.OK);
			}else { 
				throw new NullInputException(ApplicationUtil.UserNull);
			}
		}
		
		/*
		 * @Param -userId
		 * @Response -ResponseEntity of donateBookResponseDto
		 * @Description -This method is used to save the book details which is donated by the specific user.
		 * */
		@PostMapping("/book")
		public ResponseEntity<DonateBookResponseDto> donateBook(@RequestBody BookRequestDto bookRequestDto){
			log.info(":: Enter into BookController--------::donateBook()");
			return new ResponseEntity<>(bookService.donateBook(bookRequestDto),HttpStatus.OK);
		}
}
