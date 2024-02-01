package com.dotori.backend.domain.member.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dotori.backend.domain.member.type.Role;
import com.dotori.backend.domain.member.type.SocialType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "Member")
@AllArgsConstructor
public class MemberTemp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(name = "member_email")
	private String email; // 이메일

	@Column(name = "member_nickname")
	private String nickname; // 닉네임

	@Enumerated(EnumType.STRING)
	private Role role;

	@Enumerated(EnumType.STRING)
	@Column(name = "social_type")
	private SocialType socialType; // KAKAO, NAVER, GOOGLE

	@Column(name = "social_id")
	private String socialId;

	@Column(name = "profile_img")
	private String profileImg; //프사경로

	// 유저 권한 설정 메소드
	public void authorizeUser() {
		this.role = Role.USER;
	}

	//== 유저 필드 업데이트 ==//
	public void updateNickname(String updateNickname) {
		this.nickname = updateNickname;
	}

}