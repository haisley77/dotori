package com.dotori.backend.domain.room.model.dto;

import com.dotori.backend.domain.book.model.entity.Book;
import com.dotori.backend.domain.room.model.entity.Room;

import lombok.Getter;

@Getter
public class RoomDto {
	private final Long roomId;
	private final Book book;
	private final Long hostId;
	private final String title;
	private final String password;
	private final boolean isRecording;
	private final int joinCnt;
	private final int limitCnt;
	private final boolean isPublic;
	private final String sessionId;

	public RoomDto(Room room) {
		this.roomId = room.getRoomId();
		this.book = room.getBook();
		this.hostId = room.getHostId();
		this.title = room.getTitle();
		this.password = room.getPassword();
		this.isRecording = room.isRecording();
		this.joinCnt = room.getJoinCnt();
		this.limitCnt = room.getLimitCnt();
		this.isPublic = room.isPublic();
		this.sessionId = room.getSessionId();
	}
}
