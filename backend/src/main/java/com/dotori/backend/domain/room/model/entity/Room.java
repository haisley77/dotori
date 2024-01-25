package com.dotori.backend.domain.room.model.entity;

import static javax.persistence.CascadeType.*;
import static javax.persistence.GenerationType.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dotori.backend.common.entity.BaseTimeEntity;
import com.dotori.backend.domain.book.model.entity.Book;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "room")
public class Room extends BaseTimeEntity {
	@Id
	@Column(name = "room_id")
	@GeneratedValue(strategy = IDENTITY) // auto_increment
	private Long roomId;

	@ManyToOne
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;

	@OneToMany(mappedBy = "room", cascade = ALL)
	private List<RoomMember> roomMembers = new ArrayList<>();

	@Column(name = "host_id")
	private Long hostId;

	@Column(length = 20, name = "title")
	private String title;

	@Column(length = 50, name = "password")
	private String password;

	@Column(name = "is_recording")
	private boolean isRecording;

	@Column(name = "join_cnt")
	private int joinCnt;

	@Column(name = "limit_cnt")
	private int limitCnt;

	@Column(name = "is_public")
	private boolean isPublic;

	@Column(length = 50, name = "session_id")
	private String sessionId;

	@Builder
	public Room(Book book, List<RoomMember> roomMembers, Long hostId, String title, String password,
		boolean isPublic, String sessionId) {
		this.book = book;
		this.roomMembers = roomMembers;
		this.hostId = hostId;
		this.title = title;
		this.password = password;
		this.isPublic = isPublic;
		this.sessionId = sessionId;
	}
}
