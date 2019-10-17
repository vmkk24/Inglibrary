/**
 * 
 */
package com.hcl.inglibrary.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.hcl.inglibrary.dto.RequestReserveDto;
import com.hcl.inglibrary.service.BookServiceImpl;
import com.hcl.inglibrary.util.ContentTypeTestCase;

/**
 * @author SubhaMaheswaran
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

	@Mock
	BookServiceImpl bookServiceImpl;

	@InjectMocks
	BookController bookController;

	MockMvc mockMvc;
	RequestReserveDto requestReserveDto;

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
