package com.dotori.backend.members.service;

import com.dotori.backend.members.Repository.MemberRepository;
import com.dotori.backend.members.dto.*;
import com.dotori.backend.members.model.Member;
import com.dotori.backend.members.util.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.usertype.UserType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final List<SocialLoginService> loginServices;
    private final MemberRepository memberRepository;
    public LoginResponse doSocialLogin(SocialLoginRequest request) {
        SocialLoginService loginService = this.getLoginService(request.getUserType());

        SocialAuthResponse socialAuthResponse = loginService.getAccessToken(request.getCode());

        SocialMemberResponse socialMemberResponse = loginService.getMemberInfo(socialAuthResponse.getAccess_token());
        log.info("socialMemberResponse {} ", socialMemberResponse.toString());

        if (memberRepository.findByMemberId(socialMemberResponse.getId()).isEmpty()) {
            this.joinMember(
                    MemberJoinRequest.builder()
                            .memberId(socialMemberResponse.getId())
                            .memberEmail(socialMemberResponse.getEmail())
                            .memberNickname(socialMemberResponse.getNickname())
                            .userType(request.getUserType())
                            .build()
            );
        }

        Member member = memberRepository.findByMemberId(socialMemberResponse.getId())
                .orElseThrow(() -> new NotFoundException("ERROR_001", "유저 정보를 찾을 수 없습니다."));

        return LoginResponse.builder()
                .id(member.getId())
                .build();
    }

    private MemberJoinResponse joinMember(MemberJoinRequest memberJoinRequest) {
        Member member = memberRepository.save(
                Member.builder()
                        .userId(memberJoinRequest.getMemberId())
                        .userType(memberJoinRequest.getUserType())
                        .memberEmail(memberJoinRequest.getMemberEmail())
                        .memberNickname(memberJoinRequest.getMemberNickname())
                        .build()
        );

        return MemberJoinResponse.builder()
                .id(member.getId())
                .build();
    }

    private SocialLoginService getLoginService(UserType memberType){
        for (SocialLoginService loginService: loginServices) {
            if (memberType.equals(loginService.getServiceName())) {
                log.info("login service name: {}", loginService.getServiceName());
                return loginService;
            }
        }
        return new LoginServiceImpl();
    }

    public MemberResponse getMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ERROR_001", "유저 정보를 찾을 수 없습니다."));

        return MemberResponse.builder()
                .id(member.getId())
                .memberEmail(member.getMemberEmail())
                .memberNickname(member.getMemberNickname())
                .build();
    }
}
