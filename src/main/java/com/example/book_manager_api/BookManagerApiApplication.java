package com.example.book_manager_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class BookManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookManagerApiApplication.class, args);
	}

}
