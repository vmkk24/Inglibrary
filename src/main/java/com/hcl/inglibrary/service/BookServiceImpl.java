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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	BookIssuedHistoryRepository bookIssuedHistoryRepository;

	@Override
	public BookListResponseDto fetchBooks() {

		return null;
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
		log.info(":: Enter into BookServiceImpl--------::reserveBook()");
		if (requestReserveDto == null) {
			throw new CommonException(ExceptionConstants.ISSUE_BOOK_FAILED);
		}

		BookIssuedHistory bookIssuedHistory = new BookIssuedHistory();
		BeanUtils.copyProperties(requestReserveDto, bookIssuedHistory);
		ResponseReserveDto responseReserveDto = new ResponseReserveDto();

		if (requestReserveDto.getStatus().equalsIgnoreCase("Available")) {

			bookIssuedHistory.setBookId(bookId);
			bookIssuedHistory.setStatus(ExceptionConstants.ISSUED);
			bookIssuedHistory.setIssuedDate(LocalDate.now());
			bookIssuedHistory.setDueDate(LocalDate.now().plusDays(7));
			responseReserveDto.setMessage("Book issued Successfully");
		} else {

			BookIssuedHistory responseBookIssuedHistoryForPreBook = bookIssuedHistoryRepository
					.findByBookIdAndStatus(bookId, ExceptionConstants.ISSUED);
			if (responseBookIssuedHistoryForPreBook.getUserId().equals(requestReserveDto.getUserId())) {
				throw new CommonException(ExceptionConstants.RESERVE_BOOK_FAILED);
			}
			bookIssuedHistory.setBookId(bookId);
			bookIssuedHistory.setIssuedDate(responseBookIssuedHistoryForPreBook.getDueDate().plusDays(2));
			bookIssuedHistory.setDueDate(responseBookIssuedHistoryForPreBook.getDueDate().plusDays(7));
			bookIssuedHistory.setStatus("Reserved");
			responseReserveDto.setMessage("Book reserved Successfully" + " Your due date is "
					+ responseBookIssuedHistoryForPreBook.getDueDate().plusDays(7));
		}
		BookIssuedHistory responseBookIssuedHistory = bookIssuedHistoryRepository.save(bookIssuedHistory);
		if (responseBookIssuedHistory.getBookIssuedId() == null) {
			throw new CommonException(ExceptionConstants.ISSUE_BOOK_FAILED);
		}

		responseReserveDto.setStatusCode(200);
		responseReserveDto.setBookIssuedId(responseBookIssuedHistory.getBookIssuedId());

		Book responseBook = bookRepository.findByBookId(bookId);

		if (responseBook.getStatus().equalsIgnoreCase("Available")) {
			responseBook.setStatus(ExceptionConstants.ISSUED);

		} else {
			responseBook.setStatus("Reserved");

		}
		bookRepository.save(responseBook);
		return responseReserveDto;
	}

}
