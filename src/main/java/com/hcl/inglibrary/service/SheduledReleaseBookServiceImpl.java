package com.hcl.inglibrary.service;

import java.time.LocalDate;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.inglibrary.dto.SchedulderResponse;
import com.hcl.inglibrary.entity.Book;
import com.hcl.inglibrary.entity.BookIssuedHistory;
import com.hcl.inglibrary.repository.BookIssuedHistoryRepository;
import com.hcl.inglibrary.repository.BookRepository;
import com.hcl.inglibrary.utils.ApplicationConstants;

/**
 * 
 * @author sharath vemperala
 *
 */

@Service
public class SheduledReleaseBookServiceImpl implements SheduledReleaseBookService {

	private static final Logger logger = LoggerFactory.getLogger(SheduledReleaseBookService.class);

	@Autowired
	BookRepository bookRepository;

	@Autowired
	BookIssuedHistoryRepository bookIssuedHistoryRepository;

	/**
	 * 
	 * @param no prameters
	 * @return SchedulderResponse
	 */

	@Override
	public SchedulderResponse autoReleaseBook() {

		/*
		 * This will run the schedulder every day once and this will automatically
		 * release and issues the books bases on due date
		 */
		logger.info(":: Enter into SheduledReleaseBookService--------::autoReleaseBook()");
		LocalDate currentDate = LocalDate.now();
		String status = ApplicationConstants.ISSUEDED;
		Optional<List<BookIssuedHistory>> bookIssuedHistoryO = bookIssuedHistoryRepository
				.findByDueDateAndStatus(currentDate, status);
		List<BookIssuedHistory> lisBookIssuedHistories = null;
		if (bookIssuedHistoryO.isPresent()) {
			lisBookIssuedHistories = bookIssuedHistoryO.get();

		}
		List<Integer> listOfBookId = new ArrayList<Integer>();
		if (bookIssuedHistoryO.isPresent()) {
			lisBookIssuedHistories.forEach(bookIssuedHistory -> {
				bookIssuedHistory.setStatus(ApplicationConstants.AVAILABLE);
				listOfBookId.add(bookIssuedHistory.getBookId());
			});
			bookIssuedHistoryRepository.saveAll(lisBookIssuedHistories);
			List<Book> listOfBook = bookRepository.findAllById(listOfBookId);
			listOfBook.forEach(book -> {
				book.setStatus(ApplicationConstants.AVAILABLE);
			});
			bookRepository.saveAll(listOfBook);

		}

		SchedulderResponse schedulderResponse = new SchedulderResponse();
		schedulderResponse.setMessage(ApplicationConstants.SUCCESS);
		schedulderResponse.setStatusCode(HttpStatus.OK.value());
		return schedulderResponse;
	}

	/**
	 * 
	 * @param no prameters
	 * @return SchedulderResponse
	 */

	@Override
	public SchedulderResponse autoHoldBookForPreRelease() {

		/*
		 * This will run the schedulder every day once and this will automatically
		 * release and issues the books bases on due date
		 */
		logger.info(":: Enter into SheduledReleaseBookService--------::autoReleaseBook()");
		LocalDate currentDate = LocalDate.now();
		String status = ApplicationConstants.RESERVED;
		Optional<List<BookIssuedHistory>> bookIssuedHistoryO = bookIssuedHistoryRepository
				.findByDueDateAndStatus(currentDate, status);
		List<BookIssuedHistory> lisBookIssuedHistories = null;
		if (bookIssuedHistoryO.isPresent()) {
			lisBookIssuedHistories = bookIssuedHistoryO.get();

		}

		List<Integer> listOfBookId = new ArrayList<Integer>();
		if (bookIssuedHistoryO.isPresent()) {
			lisBookIssuedHistories.forEach(bookIssuedHistory -> {
				bookIssuedHistory.setStatus(ApplicationConstants.ISSUEDED);
				listOfBookId.add(bookIssuedHistory.getBookId());
			});
			bookIssuedHistoryRepository.saveAll(lisBookIssuedHistories);
			List<Book> listOfBook = bookRepository.findAllById(listOfBookId);
			listOfBook.forEach(book -> {
				book.setStatus(ApplicationConstants.ISSUEDED);
			});
			bookRepository.saveAll(listOfBook);

		}

		SchedulderResponse schedulderResponse = new SchedulderResponse();
		schedulderResponse.setMessage(ApplicationConstants.SUCCESS);
		schedulderResponse.setStatusCode(HttpStatus.OK.value());
		return schedulderResponse;

	}

}
