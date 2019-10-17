package com.hcl.inglibrary.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ContentTypeTestCase {
	private ContentTypeTestCase() {

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
