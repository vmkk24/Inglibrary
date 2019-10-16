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

import com.hcl.inglibrary.dto.RegisterRequestDto;
import com.hcl.inglibrary.service.RegisterServiceImpl;
import com.hcl.inglibrary.util.ContentTypeTestCase;

@RunWith(MockitoJUnitRunner.class)
public class RegisterControllerTest {

	@Mock
	RegisterServiceImpl registerServiceImpl;

	@InjectMocks
	RegisterController registerController;

	MockMvc mockMvc;
	RegisterRequestDto registerRequestDto;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();
		registerRequestDto = new RegisterRequestDto();
		registerRequestDto.setUserName("subha");

	}

	@Test
	public void testRegister() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/register").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(ContentTypeTestCase.asJsonString(registerRequestDto)))
				.andExpect(status().isCreated());
	}

}
