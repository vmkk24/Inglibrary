package com.hcl.inglibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InglibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InglibraryApplication.class, args);
	}

}
