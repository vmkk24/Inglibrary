package com.hcl.inglibrary.service;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.inglibrary.dto.BookListByUserResponseDto;
import com.hcl.inglibrary.dto.BookListResponseDto;
import com.hcl.inglibrary.dto.BookRequestDto;
import com.hcl.inglibrary.dto.DonateBookResponseDto;
import com.hcl.inglibrary.dto.RequestReserveDto;
import com.hcl.inglibrary.dto.ResponseReserveDto;
import com.hcl.inglibrary.entity.Book;
import com.hcl.inglibrary.entity.BookIssuedHistory;
import com.hcl.inglibrary.repository.BookIssuedHistoryRepository;
import com.hcl.inglibrary.repository.BookRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

	@Mock
	BookRepository bookRepository;

	@InjectMocks
	BookServiceImpl bookServiceImpl;

	RequestReserveDto requestReserveDto;

	@Mock
	BookIssuedHistoryRepository bookIssuedHistoryRepository;

	BookIssuedHistory bookIssuedHistory;

	Book book;
	BookRequestDto bookRequestDto;
	List<Book> books;

	List<BookIssuedHistory> listBookIssuedHistory;

	@Before
	public void setup() {

		listBookIssuedHistory = new ArrayList<>();
		bookRequestDto = new BookRequestDto();
		bookRequestDto.setAuthorName("Manisha");
		bookRequestDto.setBookName("Mani");
		bookRequestDto.setUserId(1);

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

		listBookIssuedHistory.add(bookIssuedHistory);

		book = new Book();
		book.setStatus("Issued");

		books = new ArrayList<>();
		Book book = new Book();
		book.setAuthorName("Manisha");
		book.setBookId(1);
		book.setBookName("Mani");
		book.setStatus("available");
		book.setUserId(1);

		books.add(book);
	}

	@Test
	public void reserveBook() {
		Mockito.when(bookIssuedHistoryRepository.save(Mockito.any())).thenReturn(bookIssuedHistory);
		Mockito.when(bookRepository.findByBookId(Mockito.anyInt())).thenReturn(book);
		Mockito.when(bookRepository.save(Mockito.any())).thenReturn(null);
		ResponseReserveDto responseReserveDto = bookServiceImpl.reserveBook(requestReserveDto, 1);
		Assert.assertEquals(Integer.valueOf(1), responseReserveDto.getBookIssuedId());
	}

	@Test
	public void testFetchBooks() {

		Mockito.when(bookRepository.findAll()).thenReturn(books);

		List<BookListResponseDto> actual = bookServiceImpl.fetchBooks();

		assertNotNull(actual);
	}

	@Test
	public void testFetchBookByUser() {
		Mockito.when(bookIssuedHistoryRepository.findAllByUserIdAndStatus(Mockito.anyInt(), Mockito.anyString()))
				.thenReturn(listBookIssuedHistory);
		Mockito.when(bookRepository.findByBookId(Mockito.anyInt())).thenReturn(book);
		List<BookListByUserResponseDto> response = bookServiceImpl.fetchBooksByUser(1);
		assertNotNull(response);
	}

	@Test
	public void testDonateBbooks() {
		Mockito.when(bookRepository.save(Mockito.any())).thenReturn(book);
		DonateBookResponseDto donateBookResponseDto = bookServiceImpl.donateBook(bookRequestDto);
		assertNotNull(donateBookResponseDto);
	}

}
