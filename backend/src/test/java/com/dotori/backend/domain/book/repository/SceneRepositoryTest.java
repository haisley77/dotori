package com.dotori.backend.domain.book.repository;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import com.dotori.backend.domain.book.model.entity.Book;
import com.dotori.backend.domain.book.model.entity.Scene;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SceneRepositoryTest {
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private SceneRepository sceneRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	@Transactional
	void getScenes() {
		// given
		Book savedBook = bookRepository.save(
			Book.builder()
				.title("title1")
				.author("author1")
				.bookImg("bookImg1")
				.summary("summary1")
				.roleCnt(1)
				.build()
		);

		Scene savedScene1 = Scene.builder()
			.sceneOrder(1)
			.backgroundImage("bgImg1")
			.book(savedBook)
			.build();
		Scene savedScene2 = Scene.builder()
			.sceneOrder(2)
			.backgroundImage("bgImg2")
			.book(savedBook)
			.build();
		Scene savedScene3 = Scene.builder()
			.sceneOrder(2)
			.backgroundImage("bgImg3")
			.book(savedBook)
			.build();
		List<Scene> savedScenes = new ArrayList<>();
		savedScenes.add(savedScene1);
		savedScenes.add(savedScene2);
		savedScenes.add(savedScene3);

		sceneRepository.saveAll(savedScenes);

		// when
		List<Scene> foundScenes = sceneRepository.findByBook_BookId(savedBook.getBookId());

		// then
		Assertions.assertThat(foundScenes.size()).isEqualTo(savedScenes.size());
		Assertions.assertThat(foundScenes)
			.usingRecursiveComparison()
			.isEqualTo(savedScenes);
	}
}