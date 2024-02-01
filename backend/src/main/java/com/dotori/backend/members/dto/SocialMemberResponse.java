package com.dotori.backend.members.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class SocialMemberResponse {
    private Long id;
    private String email;
    private String nickname;
	private String phoneNum;
}
