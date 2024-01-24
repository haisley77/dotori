package com.dotori.backend.entity;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member")
public class Member extends BaseTimeEntity {
	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = IDENTITY)
	private Long memberId;

	@Column(unique = true)
	private String nickname;

	@Column(name = "profile_img")
	private String profileImg;

	@OneToOne(fetch = LAZY, mappedBy = "member", cascade = ALL)
	private RoomMember roomMember;
}

