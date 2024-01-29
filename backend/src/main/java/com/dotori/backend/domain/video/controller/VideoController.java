package com.dotori.backend.domain.video.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dotori.backend.domain.video.model.dto.VideoSceneUploadRequest;
import com.dotori.backend.domain.video.service.VideoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api/videos")
@RequiredArgsConstructor
public class VideoController {
	private final VideoService videoService;

	@PostMapping
	private ResponseEntity<Void> uploadSceneVideo(
		@Validated VideoSceneUploadRequest videoSceneUploadRequest
	) {
		log.info("[videoChunkUpload] called");
		if (!videoService.uploadSceneVideo(videoSceneUploadRequest))
			return ResponseEntity.status(206).build();
		return ResponseEntity.ok().build();
	}
}
