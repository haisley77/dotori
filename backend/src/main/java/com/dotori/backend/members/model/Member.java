package com.dotori.backend.members.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.dotori.backend.members.type.MemberType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member extends BaseEntity {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50)
	private String phoneNum;

	@Column(length = 50)
	private String memberNickname;

	@Column(length = 50)
	private String memberEmail;

	@Column(columnDefinition = "ENUM('KAKAO', 'NAVER', 'GOOGLE', 'NORMAL') DEFAULT 'NORMAL'")
	@Enumerated(EnumType.STRING)
	private MemberType memberType;

}