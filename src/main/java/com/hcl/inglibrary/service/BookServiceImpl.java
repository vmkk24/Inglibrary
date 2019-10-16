package com.hcl.inglibrary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.inglibrary.dto.BookListResponseDto;
import com.hcl.inglibrary.dto.UserResponseDto;
import com.hcl.inglibrary.entity.Book;
import com.hcl.inglibrary.entity.User;
import com.hcl.inglibrary.exception.UserNotFoundException;
import com.hcl.inglibrary.repository.BookRepository;
import com.hcl.inglibrary.util.ExceptionConstants;
import org.springframework.beans.BeanUtils;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookRepository bookRepository;
	@Override
	public BookListResponseDto fetchBooks() {

/*		BookListResponseDto bookListResponseDto = new BookListResponseDto();
		List<Book> books = bookRepository.findAll();
		if(books != null) {
			books.forEach(arg0);
		BeanUtils.copyProperties(books, bookListResponseDto);
		return bookListResponseDto;
		}else {
			throw new UserNotFoundException(ExceptionConstants.userNotFound);
		}
	}
*/		return null;
	}

}
