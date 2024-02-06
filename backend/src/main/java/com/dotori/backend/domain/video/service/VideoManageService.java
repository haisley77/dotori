package com.dotori.backend.domain.video.service;

import static com.dotori.backend.domain.video.model.VideoMapper.*;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dotori.backend.domain.room.model.entity.Room;
import com.dotori.backend.domain.video.model.dto.VideoDto;
import com.dotori.backend.domain.video.model.entity.SceneVideo;
import com.dotori.backend.domain.video.model.entity.Video;
import com.dotori.backend.domain.video.repository.SceneVideoRepository;
import com.dotori.backend.domain.video.repository.VideoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoManageService {
	private final VideoRepository videoRepository;
	private final SceneVideoRepository sceneVideoRepository;

	@Transactional
	public VideoDto getVideo(Long videoId) {
		Video video = videoRepository.findById(videoId)
			.orElseThrow(() -> new EntityNotFoundException("해당하는 비디오가 존재하지 않습니다."));
		return toVideoDto(video);
	}

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