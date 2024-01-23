package com.dotori.backend.members.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import org.hibernate.usertype.UserType;

@Getter
public class SocialLoginRequest {
    @NotNull
    private UserType userType;
    @NotNull
    private String code;
}
