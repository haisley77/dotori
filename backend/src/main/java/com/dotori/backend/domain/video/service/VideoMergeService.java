package com.dotori.backend.domain.video.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;
import org.springframework.stereotype.Service;

import com.dotori.backend.domain.video.model.dto.request.VideoMergeRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoMergeService {
	private final File videoDirectory;

	// TODO 방 생성, 채팅, 역할 동기화
	public void videoMerge(VideoMergeRequest videoMergeRequest) {
		log.info("[videoMerge] called");
		try {
			merge(videoMergeRequest);
		} catch (FrameGrabber.Exception | FrameRecorder.Exception e) {
			log.error("영상 병합 중 exception 발생", e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void merge(VideoMergeRequest videoMergeRequest) throws FrameGrabber.Exception, FrameRecorder.Exception {
		List<FFmpegFrameGrabber> grabbers = new ArrayList<>();
		for (String path : videoMergeRequest.getPaths()) {
			FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(path);
			grabber.start();
			grabbers.add(grabber);
		}

		FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(
			videoDirectory.toPath().resolve("output.mp4").toFile(),
			grabbers.get(0).getImageWidth(),
			grabbers.get(0).getImageHeight()
		);
		recorder.setFrameRate(grabbers.get(0).getFrameRate());
		recorder.setVideoBitrate(grabbers.get(0).getVideoBitrate());
		recorder.setAudioChannels(grabbers.get(0).getAudioChannels());
		recorder.setFormat(grabbers.get(0).getFormat());
		recorder.start();

		for (FFmpegFrameGrabber grabber : grabbers) {
			Frame frame;
			while ((frame = grabber.grabFrame()) != null) {
				recorder.record(frame);
			}
			grabber.stop();
			grabber.close();
		}
		recorder.stop();
		recorder.close();
	}
}