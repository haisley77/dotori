package com.dotori.backend.members.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dotori.backend.NotFoundException;
import com.dotori.backend.members.Repository.MemberRepository;
import com.dotori.backend.members.dto.LoginResponse;
import com.dotori.backend.members.dto.MemberJoinRequest;
import com.dotori.backend.members.dto.MemberJoinResponse;
import com.dotori.backend.members.dto.MemberResponse;
import com.dotori.backend.members.dto.SocialAuthResponse;
import com.dotori.backend.members.dto.SocialLoginRequest;
import com.dotori.backend.members.dto.SocialMemberResponse;
import com.dotori.backend.members.model.Member;
import com.dotori.backend.members.type.MemberType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
	private final List<SocialLoginService> loginServices;
	private final MemberRepository memberRepository;

	public LoginResponse doSocialLogin(SocialLoginRequest request) {
		log.info("Received social login request: {}", request);
		SocialLoginService loginService = this.getLoginService(request.getMemberType());

		SocialAuthResponse socialAuthResponse = loginService.getAccessToken(request.getCode());

		SocialMemberResponse socialMemberResponse = loginService.getMemberInfo(socialAuthResponse.getAccess_token());
		log.info("socialMemberResponse {} ", socialMemberResponse.toString());

		if (memberRepository.findByphoneNum(socialMemberResponse.getPhoneNum()).isEmpty()) {
			this.joinMember(
				MemberJoinRequest.builder()
					.memberEmail(socialMemberResponse.getEmail())
					.memberNickname(socialMemberResponse.getNickname())
					.memberType(request.getMemberType())
					.build()
			);
		}

		Member member = memberRepository.findByphoneNum(socialMemberResponse.getPhoneNum())
			.orElseThrow(() -> new NotFoundException("ERROR_001", "유저 정보를 찾을 수 없습니다."));

		return LoginResponse.builder()
			.id(member.getId())
			.build();
	}

	private MemberJoinResponse joinMember(MemberJoinRequest memberJoinRequest) {
		Member member = memberRepository.save(
			Member.builder()
				.phoneNum(memberJoinRequest.getPhoneNum())
				.memberType(memberJoinRequest.getMemberType())
				.memberEmail(memberJoinRequest.getMemberEmail())
				.memberNickname(memberJoinRequest.getMemberNickname())
				.build()
		);

		return MemberJoinResponse.builder()
			.id(member.getId())
			.build();
	}

	private SocialLoginService getLoginService(MemberType memberType) {
		for (SocialLoginService loginService : loginServices) {
			System.out.println(loginService);
			if (memberType.equals(loginService.getServiceName())) {
				log.info("login service name: {}", loginService.getServiceName());
				return loginService;
			}
		}
		return new LoginServiceImpl();
	}

	public MemberResponse getMember(String phoneNum) {
		Member member = memberRepository.findByphoneNum(phoneNum)
			.orElseThrow(() -> new NotFoundException("ERROR_001", "유저 정보를 찾을 수 없습니다."));

		return MemberResponse.builder()
			.id(member.getId())
			.memberEmail(member.getMemberEmail())
			.memberNickname(member.getMemberNickname())
			.memberPhoneNum(member.getPhoneNum())
			.build();
	}
}
