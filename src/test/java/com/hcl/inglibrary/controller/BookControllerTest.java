
package com.hcl.inglibrary.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.hcl.inglibrary.dto.RequestReserveDto;
import com.hcl.inglibrary.dto.ResponseReserveDto;
import com.hcl.inglibrary.entity.Book;
import com.hcl.inglibrary.service.BookServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * @author SubhaMaheswaran
 *
 */
@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class BookControllerTest {

	@Mock
	BookServiceImpl bookServiceImpl;

	@InjectMocks
	BookController bookController;

	MockMvc mockMvc;

	RequestReserveDto requestReserveDto;

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
	public void testReserveBook() throws Exception {
		log.info(":: Enter into BookControllerTest--------::testReserveBook()");
		Mockito.when(bookServiceImpl.reserveBook(Mockito.any(), Mockito.anyInt())).thenReturn(responseReserveDto);
		ResponseReserveDto responseReserveDto = bookServiceImpl.reserveBook(requestReserveDto, 1);
		Assert.assertNotNull(responseReserveDto);
	}

}
