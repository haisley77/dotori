package com.dotori.backend.domain.video.service;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dotori.backend.domain.room.model.entity.Room;
import com.dotori.backend.domain.video.model.dto.VideoSceneUploadRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoService {
	private final VideoUploadService videoUploadService;
	private final VideoManageService videoManageService;

	// TODO front 기능 개발에 따라 차후 수정
	@Transactional
	public boolean uploadSceneVideo(VideoSceneUploadRequest videoSceneUploadRequest) {
		File uploadSceneVideo = videoUploadService.uploadChunkFiles(videoSceneUploadRequest);
		if (uploadSceneVideo == null) {
			return false;
		}
		// TODO room 개발 완료시 수정
		// Room room = roomService.getRoom(videoSceneUploadRequest.get(roomId));
		Room room = null;
		videoManageService.saveSceneVideo(
			room,
			videoSceneUploadRequest.getSceneOrder(),
			uploadSceneVideo.toString()
		);
		return true;
	}
}