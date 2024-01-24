package com.dotori.backend.room.model.entity;

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

import com.dotori.backend.book.model.entity.Book;
import com.dotori.backend.common.entity.BaseTimeEntity;

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
	;

	@Column(name = "host_id")
	private Long hostId;

	@Column(name = "title")
	private String title;

	@Column(name = "password")
	private String password;

	@Column(name = "is_recording")
	private boolean isRecording;

	@Column(name = "join_cnt")
	private int joinCnt;

	@Column(name = "limit_cnt")
	private int limitCnt;

	@Column(name = "is_public")
	private boolean isPublic;

	@Column(name = "session_id")
	private String sessionId;
}