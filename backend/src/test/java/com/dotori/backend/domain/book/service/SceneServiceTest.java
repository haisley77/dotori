package com.dotori.backend.domain.book.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dotori.backend.domain.book.model.dto.BookDto;
import com.dotori.backend.domain.book.model.dto.SceneDto;
import com.dotori.backend.domain.book.model.entity.Book;
import com.dotori.backend.domain.book.model.entity.Scene;
import com.dotori.backend.domain.book.repository.SceneRepository;

@ExtendWith(MockitoExtension.class)
class SceneServiceTest {
	@InjectMocks
	SceneService sceneService;
	
	@Mock
	SceneRepository sceneRepository;

	@Test
	@DisplayName("scenesService.getScenes test")
	void getScenes() {
		Book savedBook = Book.builder()
			.title("title1")
			.author("author1")
			.bookImg("bookImg1")
			.summary("summary1")
			.build();

		List<Scene> scenes = new ArrayList<>();
		scenes.add(Scene.builder()
			.book(savedBook)
			.sceneOrder(1)
			.backgroundImage("bookImg1")
			.build()
		);
		scenes.add(Scene.builder()
			.book(savedBook)
			.sceneOrder(1)
			.backgroundImage("bookImg1")
			.build()
		);
		scenes.add(Scene.builder()
			.book(savedBook)
			.sceneOrder(1)
			.backgroundImage("bookImg1")
			.build()
		);

		when(sceneRepository.findByBook_BookId(anyLong()))
			.thenReturn(scenes);

		List<SceneDto> result = sceneService.getScenesByBookId(anyLong());

		assertThat(result.size())
			.isEqualTo(scenes.size());

		assertThat(result)
			.usingRecursiveComparison()
			.ignoringFieldsOfTypes(BookDto.class)
			.isEqualTo(scenes);

		assertThat(result.get(0).getBookDto())
			.usingRecursiveComparison()
			.isEqualTo(savedBook);

		verify(sceneRepository, times(1)).findByBook_BookId(anyLong());
	}
}