package com.dotori.backend.members.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.usertype.UserType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberJoinRequest {
    private String memberId;
    private String memberNickname;
    private UserType userType;
    private String memberEmail;
}
