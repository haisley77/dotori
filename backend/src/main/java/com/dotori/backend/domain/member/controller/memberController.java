package com.dotori.backend.domain.member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dotori.backend.domain.member.Repository.MemberRepository;
import com.dotori.backend.domain.member.jwt.service.JwtService;
import com.dotori.backend.domain.member.model.MemberTemp;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class memberController {

	private JwtService jwtService;
	private MemberRepository memberRepository;

	@GetMapping("/token")
	public ResponseEntity<?> checkLoginStatus(HttpServletRequest request) {
		// JWT 검증 로직 (쿠키에서 추출, 검증 등)
		// 여기서는 예시로 쿠키의 존재 여부만 확인합니다.
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("jwt".equals(cookie.getName())) {
					return ResponseEntity.ok().body("로그인.");
				}
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인안됨");
	}

	@GetMapping("/api/detail")
	public ResponseEntity<?> getMemberInfo(HttpServletRequest request) {
		Optional<String> jwtdetail = jwtService.extractEmailFromAccessToken(request);

		if (jwtdetail.isPresent()) {
			Optional<MemberTemp> emaildetail = memberRepository.findByEmail(jwtdetail.get());

			if (emaildetail.isPresent()) {
				MemberTemp member = emaildetail.get();
				Map<String, Object> memberInfo = new HashMap<>();
				memberInfo.put("nickName", member.getNickname());
				memberInfo.put("email", member.getEmail());

				return ResponseEntity.ok(memberInfo);
			} else {
				return ResponseEntity.status(404).body("User not found");
			}
		} else {
			return ResponseEntity.status(400).body("Invalid access token");
		}
	}

}
