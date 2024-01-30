package com.dotori.backend.domain.book.service;

import static java.util.stream.Collectors.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dotori.backend.domain.book.model.dto.BookMapper;
import com.dotori.backend.domain.book.model.dto.SceneDto;
import com.dotori.backend.domain.book.repository.SceneRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SceneService {
	private final SceneRepository sceneRepository;

	@Transactional(readOnly = true)
	public List<SceneDto> getScenesByBookId(Long bookId) {
		return sceneRepository.findByBook_BookId(bookId)
			.stream()
			.map(BookMapper::toSceneDto)
			.collect(toList());
	}
}
