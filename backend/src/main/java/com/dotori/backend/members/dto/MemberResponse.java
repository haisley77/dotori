package com.dotori.backend.members.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberResponse {
    private Long id;
    private String memberNickname;
    private String memberEmail;
}