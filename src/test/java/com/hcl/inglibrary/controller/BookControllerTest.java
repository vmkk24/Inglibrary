package com.hcl.inglibrary.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.inglibrary.dto.BookListByUserResponseDto;
import com.hcl.inglibrary.dto.BookListResponseDto;
import com.hcl.inglibrary.dto.BookRequestDto;
import com.hcl.inglibrary.dto.DonateBookResponseDto;
import com.hcl.inglibrary.service.BookService;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.hcl.inglibrary.dto.RequestReserveDto;
import com.hcl.inglibrary.util.ContentTypeTestCase;

/**
 * @author Manisha Yadav
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

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
		if(userId != null) {
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

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
		requestReserveDto = new RequestReserveDto();
		requestReserveDto.setUserId(1);
		requestReserveDto.setStatus("available");
	}

	@Test
	public void testReserveBook() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("books/{bookId}", 1).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(ContentTypeTestCase.asJsonString(requestReserveDto)))
				.andExpect(status().isOk());
	}

}
