package com.dotori.backend.domain.book.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dotori.backend.domain.book.model.entity.Book;
import com.dotori.backend.domain.book.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;

	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	public Book getBook(Long bookId) {
		Optional<Book> book = bookRepository.findById(bookId);

		//FIXME 해당 책 정보가 없는 경우 처리 필요
		if (book.isEmpty()) {
			return null;
		}

		return book.get();
	}
}
