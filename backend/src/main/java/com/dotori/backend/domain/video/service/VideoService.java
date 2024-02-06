package com.dotori.backend.domain.video.service;

import java.io.File;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dotori.backend.domain.room.model.entity.Room;
import com.dotori.backend.domain.room.repository.RoomRepository;
import com.dotori.backend.domain.video.model.dto.VideoDto;
import com.dotori.backend.domain.video.model.dto.VideoSceneUploadRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoService {
	private final VideoUploadService videoUploadService;
	private final VideoDownloadService videoDownloadService;
	private final VideoManageService videoManageService;

	private final RoomRepository roomRepository;

	@Transactional
	public boolean uploadSceneVideo(VideoSceneUploadRequest videoSceneUploadRequest) {
		File uploadSceneVideo = videoUploadService.uploadChunkFiles(videoSceneUploadRequest);
		if (uploadSceneVideo == null) {
			return false;
		}

		Room room = roomRepository.findById(videoSceneUploadRequest.getRoomId())
			.orElseThrow(EntityNotFoundException::new);

		videoManageService.saveSceneVideo(room, videoSceneUploadRequest.getSceneOrder(), uploadSceneVideo.toString());
		return true;
	}

	@Transactional(readOnly = true)
	public File downloadVideo(Long videoId) {
		log.info("[downloadVideo] called");
		VideoDto videoDto = videoManageService.getVideo(videoId);
		return videoDownloadService.downloadVideo(videoDto.getPath());
	}
}