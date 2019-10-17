package com.hcl.inglibrary.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.inglibrary.service.BookService;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

	@Mock
	BookService bookService;
	@InjectMocks
	BookController bookController;
	
	@Test
	public void testGetBooks() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBooksByUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDonateBook() {
		fail("Not yet implemented");
	}

}
