
package com.hcl.inglibrary.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.hcl.inglibrary.dto.BookListByUserResponseDto;
import com.hcl.inglibrary.dto.BookListResponseDto;
import com.hcl.inglibrary.dto.BookRequestDto;
import com.hcl.inglibrary.dto.DonateBookResponseDto;
import com.hcl.inglibrary.dto.RequestReserveDto;
import com.hcl.inglibrary.dto.ResponseReserveDto;
import com.hcl.inglibrary.entity.Book;
import com.hcl.inglibrary.service.BookService;
import com.hcl.inglibrary.service.BookServiceImpl;
import com.hcl.inglibrary.util.ContentTypeTestCase;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Manisha Yadav
 *
 */
@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class BookControllerTest {

	@Mock
	BookServiceImpl bookServiceImpl;
	@Mock
	BookService bookService;
	@InjectMocks
	BookController bookController;
	MockMvc mockMvc;

	RequestReserveDto requestReserveDto;

	@Test
	public void testGetBooks() {
		List<BookListResponseDto> listOfBookListResponseDto = new ArrayList<>();
		BookListResponseDto bookListResponseDto = new BookListResponseDto();
		bookListResponseDto.setAuthorName("manisha");
		bookListResponseDto.setBookId(1);
		bookListResponseDto.setBookName("mani");
		bookListResponseDto.setStatus("Available");
		listOfBookListResponseDto.add(bookListResponseDto);
		Mockito.when(bookService.fetchBooks()).thenReturn(listOfBookListResponseDto);
		ResponseEntity<List<BookListResponseDto>> actual = bookController.getBooks();

		assertNotNull(actual);
	}

	@Test
	public void testGetBooksByUser() {
		Integer userId = 12;
		if (userId != null) {
			List<BookListByUserResponseDto> listOfBookListByUserResponseDto = new ArrayList<>();
			BookListByUserResponseDto bookListByUserResponseDto = new BookListByUserResponseDto();
			bookListByUserResponseDto.setAuthorName("Manisha");
			bookListByUserResponseDto.setBookId(1);
			bookListByUserResponseDto.setBookName("Mani");
			listOfBookListByUserResponseDto.add(bookListByUserResponseDto);
			Mockito.when(bookService.fetchBooksByUser(Mockito.anyInt())).thenReturn(listOfBookListByUserResponseDto);

			ResponseEntity<List<BookListByUserResponseDto>> actual = bookController.getBooksByUser(userId);
			assertNotNull(actual);

		}
	}

	@Test
	public void testDonateBook() {

		DonateBookResponseDto donateBookResponseDto = new DonateBookResponseDto();
		donateBookResponseDto.setMessage("success");
		donateBookResponseDto.setStatusCode(200);

		Mockito.when(bookService.donateBook((Mockito.any()))).thenReturn(donateBookResponseDto);

		BookRequestDto bookRequestDto = new BookRequestDto();
		bookRequestDto.setAuthorName("Manisha");
		bookRequestDto.setBookName("Mani");
		bookRequestDto.setUserId(1);
		ResponseEntity<DonateBookResponseDto> actual = bookController.donateBook(bookRequestDto);
		assertNotNull(actual);
	}

	Book book;

	ResponseReserveDto responseReserveDto;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
		book = new Book();
		book.setBookId(1);
		book.setStatus("Available");

		requestReserveDto = new RequestReserveDto();

		requestReserveDto.setUserId(1);
		requestReserveDto.setStatus("Available");

		responseReserveDto = new ResponseReserveDto();
		responseReserveDto.setBookIssuedId(1);

	}

	@Test
	public void testReserveBooks() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/books/books/{bookId}", 1).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(ContentTypeTestCase.asJsonString(requestReserveDto)))
				.andExpect(status().isOk());
	}

	@Test
	public void testFetchBooksByUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/books/{userId}", 1).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
