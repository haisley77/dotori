package com.dotori.backend.domain.video.model;

import com.dotori.backend.domain.video.model.dto.SceneVideoDto;
import com.dotori.backend.domain.video.model.dto.VideoDto;
import com.dotori.backend.domain.video.model.entity.SceneVideo;
import com.dotori.backend.domain.video.model.entity.Video;

public class VideoMapper {
	public static VideoDto toVideoDto(Video video) {
		return new VideoDto(
			video.getVideoId(),
			video.getPath()
		);
	}

	public static SceneVideoDto toSceneVideoDto(SceneVideo sceneVideo) {
		return new SceneVideoDto(
			sceneVideo.getSceneVideoId(),
			sceneVideo.getSceneOrder(),
			sceneVideo.getPath()
		);
	}
}
