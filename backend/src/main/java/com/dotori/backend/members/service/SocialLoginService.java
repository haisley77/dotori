package com.dotori.backend.members.service;

import com.dotori.backend.members.dto.SocialAuthResponse;
import com.dotori.backend.members.dto.SocialMemberResponse;
import com.dotori.backend.members.type.UserType;
import org.springframework.stereotype.Service;

@Service
public interface SocialLoginService {
    UserType getServiceName();
    SocialAuthResponse getAccessToken(String authorizationCode);
    SocialMemberResponse getMemberInfo(String accessToken);
}
