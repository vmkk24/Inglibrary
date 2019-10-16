package com.hcl.inglibrary.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

	@Setter
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public class ResponseError {
		
		private String message;
		private int statusCode;

	}	


