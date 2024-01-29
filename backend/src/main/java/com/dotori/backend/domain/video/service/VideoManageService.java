package com.dotori.backend.domain.video.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dotori.backend.domain.room.model.entity.Room;
import com.dotori.backend.domain.video.model.entity.SceneVideo;
import com.dotori.backend.domain.video.repository.SceneVideoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoManageService {
	private final SceneVideoRepository sceneVideoRepository;

	@Transactional
	public SceneVideo saveSceneVideo(Room room, int sceneOrder, String savedPath) {
		return sceneVideoRepository.save(
			SceneVideo.builder()
				.room(room)
				.sceneOrder(sceneOrder)
				.path(savedPath)
				.build()
		);
	}
}