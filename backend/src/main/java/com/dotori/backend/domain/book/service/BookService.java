package com.dotori.backend.domain.book.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dotori.backend.domain.book.model.dto.BookDto;
import com.dotori.backend.domain.book.model.dto.BookMapper;
import com.dotori.backend.domain.book.model.dto.response.GetBooksResponse;
import com.dotori.backend.domain.book.model.entity.Book;
import com.dotori.backend.domain.book.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;

	public GetBooksResponse getBooks() {

		List<Book> bookList = bookRepository.findAll();
		List<BookDto> books = new LinkedList<>();

		for (Book book : bookList) {
			books.add(BookMapper.toBookDto(book));
		}

		return GetBooksResponse.builder().books(books).build();
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
