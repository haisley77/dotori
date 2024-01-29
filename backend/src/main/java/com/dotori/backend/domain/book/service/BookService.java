package com.dotori.backend.domain.book.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.dotori.backend.domain.book.model.dto.BookDto;
import com.dotori.backend.domain.book.model.dto.BookMapper;
import com.dotori.backend.domain.book.model.dto.RoleDto;
import com.dotori.backend.domain.book.model.dto.response.GetBookResponse;
import com.dotori.backend.domain.book.model.dto.response.GetBooksResponse;
import com.dotori.backend.domain.book.model.entity.Book;
import com.dotori.backend.domain.book.model.entity.Role;
import com.dotori.backend.domain.book.repository.BookRepository;
import com.dotori.backend.domain.book.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;

	private final RoleRepository roleRepository;

	public GetBooksResponse getBooks() {

		List<Book> bookList = bookRepository.findAll();
		List<BookDto> books = new LinkedList<>();

		for (Book book : bookList) {
			books.add(BookMapper.toBookDto(book));
		}
		return GetBooksResponse.builder().books(books).build();
	}

	public GetBookResponse getBook(Long bookId) {
		Optional<Book> findBook = bookRepository.findById(bookId);

		if (findBook.isEmpty()) {
			return null;
		}
		BookDto bookDto = BookMapper.toBookDto(findBook.get());

		List<Role> roleList = roleRepository.findByBook_BookId(bookId);
		List<RoleDto> roles = new LinkedList<>();

		for (Role role : roleList) {
			roles.add(BookMapper.toRoleDto(role));
		}

		return GetBookResponse.builder().book(bookDto).roles(roles).build();
	}
}