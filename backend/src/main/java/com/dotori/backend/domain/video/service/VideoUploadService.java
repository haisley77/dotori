package com.dotori.backend.domain.video.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dotori.backend.domain.video.model.dto.VideoSceneUploadRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoUploadService {
	@Value("${videos.path.scene}")
	String scenePath;

	@Value("${videos.path.chunk}")
	String chunkPath;

	private File sceneDirectory;

	private File chunkDirectory;

	private final ResourceLoader resourceLoader;

	public static final String TEMP_PART_NAME = "temp.part";

	@PostConstruct
	public void init() throws IOException {
		log.info("[PostConstruct] called");
		sceneDirectory = resourceLoader.getResource("file:" + scenePath).getFile();
		chunkDirectory = resourceLoader.getResource("file:" + chunkPath).getFile();
		log.info("sceneDirectory = " + sceneDirectory);
		log.info("chunkDirectory = " + chunkDirectory);
	}

	public File uploadChunkFiles(VideoSceneUploadRequest videoSceneUploadRequest) {
		log.info("[uploadChunkFiles] called");

		File tempDirectory = uploadChunkFile(
			videoSceneUploadRequest.getFileName(),
			videoSceneUploadRequest.getChunkFile(),
			videoSceneUploadRequest.getChunkNumber()
		);

		File uploadedFile;
		if (videoSceneUploadRequest.isEnd()) {
			String extractFileExtension = extractFileExtension(videoSceneUploadRequest.getFileName());
			uploadedFile = mergeChunkFiles(videoSceneUploadRequest.getFileName(), tempDirectory, extractFileExtension);
			deleteChunkFiles(tempDirectory);
			return uploadedFile;
		}
		return null;
	}

	private File uploadChunkFile(String fileName, MultipartFile chunkFile, int chunkNumber) {
		log.info("[uploadChunkFile] called");
		Path savedDirectory = chunkDirectory.toPath().resolve(fileName);
		String chunkFileName = TEMP_PART_NAME + chunkNumber;

		try {
			if (savedDirectory.toFile().mkdirs()) {
				log.info("{} directory is created", savedDirectory);
			}
			Files.write(savedDirectory.resolve(chunkFileName), chunkFile.getBytes());
		} catch (RuntimeException e) {
			log.info("chunk file upload failed", e);
			throw e;
		} catch (IOException e) {
			log.info("chunk file upload failed", e);
			throw new RuntimeException(e.getMessage());
		}
		return savedDirectory.toFile();
	}

	private String extractFileExtension(String originalFileName) {
		int idx = originalFileName.lastIndexOf(".");
		if (idx > 0) {
			return originalFileName.substring(idx);
		}
		return ".mkv";
	}

	private File mergeChunkFiles(String fileName, File tempDirectory, String fileExtension) {
		log.info("[mergeChunkFiles] called");
		File output;
		try {
			output = sceneDirectory.toPath()
				.resolve(fileName + fileExtension)
				.toFile();

			if (output.exists()) {
				output.delete();
			}
			output.createNewFile();

			for (int i = 0; i < tempDirectory.listFiles().length; i++) {
				Files.write(
					output.toPath(),
					Files.readAllBytes(tempDirectory.toPath().resolve(TEMP_PART_NAME + i)),
					StandardOpenOption.APPEND
				);
			}
		} catch (IOException e) {
			log.info("[mergeChunkFiles] failed");
			log.info("exception thrown: ", e);
			throw new RuntimeException(e);
		}
		log.info("[mergeChunkFiles] completed scene video is merged");
		return output;
	}

	private void deleteChunkFiles(File file) {
		log.info("[VideoService.deleteChunkFile] called");
		if (file == null || file.delete()) {
			log.info(file == null ? "파일이 존재하지 않습니다." : "{}: 파일이 삭제되었습니다.", file);
			return;
		}
		Arrays.stream(file.listFiles())
			.forEach(this::deleteChunkFiles);
		file.delete();
		log.info("[VideoService.deleteChunkFile] completed");
	}
}