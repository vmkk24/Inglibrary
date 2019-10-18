package com.hcl.inglibrary.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.inglibrary.exception.CommonException;

public class ContentTypeTestCase {
	private ContentTypeTestCase() {

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new CommonException(e.toString());
		}
	}

}
