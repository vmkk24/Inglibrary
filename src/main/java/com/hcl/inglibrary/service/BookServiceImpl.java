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
/**
 * 
 * @author Manisha Yadav
 * @apiNote This class is used to get/save the books from/to our library management system. 
 */
@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookRepository bookRepository;
	
	/*
	 * @Param -no param
	 * @Response -list of books
	 * @Exception -Books not found
	 * @Description -This method is used to fetch all the list of books which is available in the library.
	 * */
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
	
	/*
	 * @Param -bookRequestDto
	 * @Response -donateBookResponseDto
	 * @Exception -Books not found
	 * @Description -This method is used to save the book details which is donated by the specific user.
	 * */
	@Override
	public DonateBookResponseDto donateBook(BookRequestDto bookRequestDto) {

		Book book= new Book();
		book.setAuthorName(bookRequestDto.getAuthorName());
		book.setBookName(bookRequestDto.getBookName());
		book.setStatus(ApplicationUtil.defaultBookStatus);
		bookRepository.save(book);
		
		DonateBookResponseDto donateBookResponseDto = new DonateBookResponseDto();
		donateBookResponseDto.setMessage(ApplicationUtil.donateBookResponseDtoMsg);
		donateBookResponseDto.setStatusCode(200);
		return donateBookResponseDto;
	}

	/*
	 * @Param -userId
	 * @Response -list of books
	 * @Exception -Books not found
	 * @Description -This method is used to fetch the book details which is issued to the specific user.
	 * */
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
