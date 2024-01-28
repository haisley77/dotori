package com.dotori.backend.domain.book.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dotori.backend.domain.book.model.entity.Book;
import com.dotori.backend.domain.book.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;

	@GetMapping
	public ResponseEntity<List<Book>> getBooks() {
		return new ResponseEntity<>(bookService.getBooks(), OK);
	}
}
