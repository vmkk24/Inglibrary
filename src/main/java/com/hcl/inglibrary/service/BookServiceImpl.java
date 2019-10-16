package com.hcl.inglibrary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.inglibrary.dto.BookListResponseDto;
import com.hcl.inglibrary.entity.Book;
import com.hcl.inglibrary.exception.BooksNotFoundException;
import com.hcl.inglibrary.repository.BookRepository;
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
	

}
