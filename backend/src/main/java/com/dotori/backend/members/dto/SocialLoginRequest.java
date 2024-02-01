package com.dotori.backend.members.dto;

import com.dotori.backend.members.type.MemberType;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SocialLoginRequest {
	@NotNull
	private MemberType memberType;
	@NotNull
	private String code;
}
