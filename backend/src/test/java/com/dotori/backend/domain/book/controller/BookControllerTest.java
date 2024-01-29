package com.dotori.backend.domain.book.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.dotori.backend.domain.book.model.entity.Book;
import com.dotori.backend.domain.book.model.entity.Role;
import com.dotori.backend.domain.book.repository.BookRepository;
import com.dotori.backend.domain.book.repository.RoleRepository;
import com.dotori.backend.domain.book.service.BookService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BookControllerTest {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookService bookService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private MockMvc mockMvc;

	@DisplayName("책 목록 조회 테스트")
	@Test
	void getBooks() throws Exception {
		//given
		Book book1 = Book.builder()
			.title("자서전 2")
			.author("조석현")
			.bookImg("책 1 이미지")
			.roleCnt(4)
			.summary("줄거리 1")
			.build();

		Book book2 = Book.builder()
			.title("자서전 2")
			.bookImg("책 2 이미지")
			.roleCnt(4)
			.author("조현석")
			.summary("줄거리 2")
			.build();

		bookRepository.save(book1);
		bookRepository.save(book2);

		//when
		mockMvc.perform(get("/api/books")).andDo(print()).andExpect(status().isOk());

		//then
	}

	@DisplayName("책 정보 조회 테스트")
	@Test
	void getBook() throws Exception {
		//given
		Book book1 = Book.builder()
			.title("자서전 2")
			.author("조석현")
			.bookImg("책 1 이미지")
			.roleCnt(4)
			.summary("줄거리 1")
			.build();

		bookRepository.save(book1);

		Role role1 = Role.builder().book(book1).maskPath("탈 주소 1").name("역할 1").build();
		Role role2 = Role.builder().book(book1).maskPath("탈 주소 2").name("역할 2").build();

		roleRepository.save(role1);
		roleRepository.save(role2);

		//when

		//then
		mockMvc.perform(get("/api/books/1")).andDo(print()).andExpect(status().isOk());
	}

}