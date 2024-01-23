package com.dotori.backend.members.dto;

import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class SocialMemberResponse {
    private Long id;
    private String email;
    private String nickname;
}
