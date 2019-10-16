package com.hcl.inglibrary.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookRequestDto {

	private Integer userId;
	private String bookName;
	private String authorName;
}
