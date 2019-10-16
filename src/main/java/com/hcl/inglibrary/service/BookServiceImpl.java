package com.hcl.inglibrary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.inglibrary.dto.BookListByUserResponseDto;
import com.hcl.inglibrary.dto.BookListResponseDto;
import com.hcl.inglibrary.dto.BookRequestDto;
import com.hcl.inglibrary.dto.DonateBookResponseDto;
import com.hcl.inglibrary.entity.Book;
import com.hcl.inglibrary.exception.BooksNotFoundException;
import com.hcl.inglibrary.repository.BookRepository;
import com.hcl.inglibrary.util.ApplicationUtil;
import com.hcl.inglibrary.util.ExceptionConstants;
import org.springframework.beans.BeanUtils;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookRepository bookRepository;
	
	@Override
	public List<BookListResponseDto> fetchBooks() {

		List<BookListResponseDto> bookListResponseDto = new ArrayList<>();
		List<Book> books = bookRepository.findAll();
		if(books != null) {
			books.forEach(book->{
				BookListResponseDto bookResponseDto = new BookListResponseDto();
				BeanUtils.copyProperties(book, bookResponseDto);
				bookListResponseDto.add(bookResponseDto);
			});
			return bookListResponseDto;
		}else {
			throw new BooksNotFoundException(ExceptionConstants.booksNotFound);
		}
	}
	
	@Override
	public DonateBookResponseDto donateBook(BookRequestDto bookRequestDto) {

		Book book= new Book();
		book.setAuthorName(bookRequestDto.getAuthorName());
		book.setBookName(bookRequestDto.getBookName());
		book.setUserId(bookRequestDto.getUserId());
		book.setStatus(ApplicationUtil.defaultBookStatus);
		bookRepository.save(book);
		
		DonateBookResponseDto donateBookResponseDto = new DonateBookResponseDto();
		donateBookResponseDto.setMessage(ApplicationUtil.donateBookResponseDtoMsg);
		donateBookResponseDto.setStatusCode(200);
		return donateBookResponseDto;
	}

	@Override
	public List<BookListByUserResponseDto> fetchBooksByUser(Integer userId) {
		List<BookListByUserResponseDto> BookListByUserResponseDto = new ArrayList<>();
		List<Book> books = bookRepository.findByUserId(userId);
		if(books != null) {
			books.forEach(book->{
				BookListByUserResponseDto bookByUserResponseDto = new BookListByUserResponseDto();
				BeanUtils.copyProperties(book, bookByUserResponseDto);
				BookListByUserResponseDto.add(bookByUserResponseDto);
			});
			return BookListByUserResponseDto;
		}else {
			throw new BooksNotFoundException(ExceptionConstants.booksNotFound);
		}
	}
	

}
