package com.dotori.backend.domain.video.model.dto.request;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class VideoMergeRequest {
	// TODO : api 요청 / 응답에 따라 변경 예정
	private final List<String> paths;
}
