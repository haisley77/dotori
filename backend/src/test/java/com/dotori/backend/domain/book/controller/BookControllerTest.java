package com.dotori.backend.domain.book.controller;

import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

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
import com.dotori.backend.domain.book.model.entity.Scene;
import com.dotori.backend.domain.book.repository.BookRepository;
import com.dotori.backend.domain.book.repository.RoleRepository;
import com.dotori.backend.domain.book.repository.SceneRepository;
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
	private SceneRepository sceneRepository;

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

	@Test
	@DisplayName("책에 해당하는 장면목록 가져오기 - 성공")
	void getScenesTest() throws Exception {
		Book book = Book.builder()
			.title("title1")
			.author("author1")
			.roleCnt(1)
			.bookImg("bookImg1")
			.summary("summary1")
			.build();
		bookRepository.save(book);

		List<Scene> scenes = new ArrayList<>();
		Scene scene1 = Scene.builder()
			.backgroundImage("bgImg1")
			.sceneOrder(1)
			.book(book)
			.build();

		Scene scene2 = Scene.builder()
			.backgroundImage("bgImg2")
			.sceneOrder(2)
			.book(book)
			.build();

		Scene scene3 = Scene.builder()
			.backgroundImage("bgImg3")
			.sceneOrder(3)
			.book(book)
			.build();
		scenes.add(scene1);
		scenes.add(scene2);
		scenes.add(scene3);
		sceneRepository.saveAll(scenes);

		mockMvc.perform(get("/api/books/{bookId}/scenes", book.getBookId()))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON))
			.andExpect(jsonPath("$.scenes.size()").value(3L))
			.andExpect(jsonPath("$.scenes[0].sceneId").value(scene1.getSceneId()))
			.andExpect(jsonPath("$.scenes[0].sceneOrder").value(scene1.getSceneOrder()))
			.andExpect(jsonPath("$.scenes[0].backgroundImage").value(scene1.getBackgroundImage()))
			.andExpect(jsonPath("$.scenes[1].sceneId").value(scene2.getSceneId()))
			.andExpect(jsonPath("$.scenes[1].sceneOrder").value(scene2.getSceneOrder()))
			.andExpect(jsonPath("$.scenes[1].backgroundImage").value(scene2.getBackgroundImage()))
			.andExpect(jsonPath("$.scenes[2].sceneId").value(scene3.getSceneId()))
			.andExpect(jsonPath("$.scenes[2].sceneOrder").value(scene3.getSceneOrder()))
			.andExpect(jsonPath("$.scenes[2].backgroundImage").value(scene3.getBackgroundImage()))
			.andDo(print());
	}
}