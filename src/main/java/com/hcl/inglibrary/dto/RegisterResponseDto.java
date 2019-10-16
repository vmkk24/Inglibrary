package com.hcl.inglibrary.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class RegisterResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;
	private Integer statusCode;
}
