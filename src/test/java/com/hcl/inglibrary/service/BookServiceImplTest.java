package com.hcl.inglibrary.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import com.hcl.inglibrary.dto.BookListResponseDto;
import com.hcl.inglibrary.dto.BookRequestDto;
import com.hcl.inglibrary.dto.DonateBookResponseDto;
import com.hcl.inglibrary.entity.Book;
import com.hcl.inglibrary.repository.BookRepository;
import com.hcl.inglibrary.util.ApplicationUtil;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

	@Mock
	BookRepository bookRepository;
	@InjectMocks
	BookServiceImpl bookServiceImpl;

	@Test
	public void testFetchBooks() {

		List<BookListResponseDto> bookListResponseDto = new ArrayList<BookListResponseDto>();
		List<Book> books = new ArrayList<>();
		Book book = new Book();
		book.setAuthorName("Manisha");
		book.setBookId(1);
		book.setBookName("Mani");
		book.setStatus("available");
		book.setUserId(1);

		books.add(book);

		Mockito.when(bookRepository.findAll()).thenReturn(books);
		if (books != null) {
			books.forEach(book1 -> {
				BookListResponseDto bookResponseDto = new BookListResponseDto();
				BeanUtils.copyProperties(book1, bookResponseDto);
				bookListResponseDto.add(bookResponseDto);

			});
			List<BookListResponseDto> actual = bookServiceImpl.fetchBooks();

			assertNotNull(actual);
		}
	}

	@Test
	public void testDonateBook() {

		DonateBookResponseDto donateBookResponseDto = new DonateBookResponseDto();
		donateBookResponseDto.setMessage(ApplicationUtil.DONATE_BOOK_RESPONSE_DTO_MESSAGE);
		donateBookResponseDto.setStatusCode(200);

		BookRequestDto bookRequestDto = new BookRequestDto();
		bookRequestDto.setAuthorName("Manisha");
		bookRequestDto.setBookName("Mani");
		bookRequestDto.setUserId(1);
		DonateBookResponseDto actual = bookServiceImpl.donateBook(bookRequestDto);

		assertNotNull(actual);
	}

}
