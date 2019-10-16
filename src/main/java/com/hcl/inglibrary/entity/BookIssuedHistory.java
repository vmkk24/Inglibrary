package com.hcl.inglibrary.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BookIssuedHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookIssuedId;
	private Integer userId;
	private Integer bookId;
	private LocalDate issuedDate;
	private LocalDate dueDate;
	private String status;
}
