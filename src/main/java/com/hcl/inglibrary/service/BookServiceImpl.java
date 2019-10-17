package com.hcl.inglibrary.service;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.inglibrary.dto.BookListResponseDto;
import com.hcl.inglibrary.dto.RequestReserveDto;
import com.hcl.inglibrary.dto.ResponseReserveDto;
import com.hcl.inglibrary.entity.Book;
import com.hcl.inglibrary.entity.BookIssuedHistory;
import com.hcl.inglibrary.exception.CommonException;
import com.hcl.inglibrary.repository.BookIssuedHistoryRepository;
import com.hcl.inglibrary.repository.BookRepository;
import com.hcl.inglibrary.util.ExceptionConstants;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	BookIssuedHistoryRepository bookIssuedHistoryRepository;

	private static final String BOOKED = "issued";

	@Override
	public BookListResponseDto fetchBooks() {

		/*
		 * BookListResponseDto bookListResponseDto = new BookListResponseDto();
		 * List<Book> books = bookRepository.findAll(); if(books != null) {
		 * books.forEach(arg0); BeanUtils.copyProperties(books, bookListResponseDto);
		 * return bookListResponseDto; }else { throw new
		 * UserNotFoundException(ExceptionConstants.userNotFound); } }
		 */ return null;
	}

	/*
	 * This method is used to reserve or preReserve the book.
	 * 
	 * @Body RequestReserveDto
	 * 
	 * @return ResponseReserveDto
	 * 
	 * @Exception RESERVE_BOOK_FAILED will throw when the request data is empty
	 */

	@Override
	public ResponseReserveDto reserveBook(RequestReserveDto requestReserveDto, Integer bookId) {
		if (requestReserveDto == null) {
			throw new CommonException(ExceptionConstants.RESERVE_BOOK_FAILED);
		}

		BookIssuedHistory bookIssuedHistory = new BookIssuedHistory();
		BeanUtils.copyProperties(requestReserveDto, bookIssuedHistory);
		ResponseReserveDto responseReserveDto = new ResponseReserveDto();

		if (requestReserveDto.getStatus().equalsIgnoreCase("available")) {

			bookIssuedHistory.setBookId(bookId);
			bookIssuedHistory.setStatus(BOOKED);
			bookIssuedHistory.setIssuedDate(LocalDate.now());
			bookIssuedHistory.setDueDate(LocalDate.now().plusDays(7));
			responseReserveDto.setMessage("Book issued Successfully");
		} else {

			BookIssuedHistory responseBookIssuedHistoryForPreBook = bookIssuedHistoryRepository
					.findByBookIdAndStatus(bookId, BOOKED);
			bookIssuedHistory.setBookId(bookId);
			bookIssuedHistory.setIssuedDate(responseBookIssuedHistoryForPreBook.getDueDate().plusDays(2));
			bookIssuedHistory.setDueDate(responseBookIssuedHistoryForPreBook.getDueDate().plusDays(7));
			bookIssuedHistory.setStatus("reserved");
			responseReserveDto.setMessage("Book reserved Successfully");
		}
		BookIssuedHistory responseBookIssuedHistory = bookIssuedHistoryRepository.save(bookIssuedHistory);
		if (responseBookIssuedHistory.getBookIssuedId() == null) {
			throw new CommonException(ExceptionConstants.RESERVE_BOOK_FAILED);
		}

		responseReserveDto.setStatusCode(200);
		responseReserveDto.setBookIssuedId(responseBookIssuedHistory.getBookIssuedId());

		Book responseBook = bookRepository.findByBookId(bookId);

		if (responseBook.getStatus().equalsIgnoreCase("available")) {
			responseBook.setStatus(BOOKED);

		} else {
			responseBook.setStatus("reserved");

		}
		bookRepository.save(responseBook);
		return responseReserveDto;
	}

}
