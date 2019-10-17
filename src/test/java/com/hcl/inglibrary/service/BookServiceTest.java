/**
 * 
 */
package com.hcl.inglibrary.service;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.inglibrary.dto.RequestReserveDto;
import com.hcl.inglibrary.dto.ResponseReserveDto;
import com.hcl.inglibrary.entity.Book;
import com.hcl.inglibrary.entity.BookIssuedHistory;
import com.hcl.inglibrary.repository.BookIssuedHistoryRepository;
import com.hcl.inglibrary.repository.BookRepository;

/**
 * @author SubhaMaheswaran
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
	@Mock
	BookRepository bookRepository;

	@Mock
	BookIssuedHistoryRepository bookIssuedHistoryRepository;

	@InjectMocks
	BookServiceImpl bookServiceImpl;

	RequestReserveDto requestReserveDto;

	BookIssuedHistory bookIssuedHistory;

	Book book;

	@Before
	public void setup() {

		requestReserveDto = new RequestReserveDto();
		requestReserveDto.setUserId(1);
		requestReserveDto.setStatus("available");

		bookIssuedHistory = new BookIssuedHistory();
		bookIssuedHistory.setBookId(1);
		bookIssuedHistory.setBookIssuedId(1);
		bookIssuedHistory.setDueDate(LocalDate.now().plusDays(7));
		bookIssuedHistory.setIssuedDate(LocalDate.now());
		bookIssuedHistory.setStatus("Reserved");
		bookIssuedHistory.setUserId(1);

		book = new Book();
		book.setStatus("Issued");
	}

	@Test
	public void reserveBook() {
		Mockito.when(bookIssuedHistoryRepository.save(Mockito.any())).thenReturn(bookIssuedHistory);
		Mockito.when(bookRepository.findByBookId(Mockito.anyInt())).thenReturn(book);
		Mockito.when(bookRepository.save(Mockito.any())).thenReturn(null);
		ResponseReserveDto responseReserveDto = bookServiceImpl.reserveBook(requestReserveDto, 1);
		Assert.assertEquals(Integer.valueOf(1), responseReserveDto.getBookIssuedId());
	}
}
