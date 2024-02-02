package com.dotori.backend.domain.member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dotori.backend.domain.member.jwt.service.JwtService;
import com.dotori.backend.domain.member.model.MemberTemp;
import com.dotori.backend.domain.member.redis.RedisService;
import com.dotori.backend.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class memberController {

	private JwtService jwtService;
	private MemberRepository memberRepository;

	private RedisService redisService;

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

	@GetMapping("/detail")
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
				return ResponseEntity.status(404).body("가입된 사용자데이터를 찾을수없습니다.");
			}
		} else {
			return ResponseEntity.status(400).body("유효한 토큰을 찾을수없습니다");
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
		// JWT 토큰 추출
		Optional<String> accessToken = jwtService.extractAccessToken(request);

		accessToken.ifPresent(token -> {
			// 쿠키 제거
			jwtService.removeAccessToken(response);

			// Refresh Token 제거
			jwtService.extractEmail(token).ifPresent(redisService::removeRefreshToken);
		});

		return ResponseEntity.ok("로그아웃완료");
	}

	@PutMapping("/update_nickname")
	public ResponseEntity<?> updateNickname(HttpServletRequest request, @RequestParam String newNickname) {
		// JWT에서 이메일 추출
		Optional<String> jwtemail = jwtService.extractEmailFromAccessToken(request);
		if (!jwtemail.isPresent()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body("유효하지않거나 만료된 토큰입니다.");
		}

		// 사용자 정보 찾기 및 닉네임 업데이트
		String email = jwtemail.get();
		MemberTemp member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new EntityNotFoundException("해당이메일은 가입되지않은 이메일입니다: " + email));

		member.updateNickname(newNickname);
		memberRepository.save(member);

		return ResponseEntity.ok("닉네임 변경완료.");
	}

	@PutMapping("/update_profileimg")
	public ResponseEntity<?> updateProfileImg(HttpServletRequest request, @RequestParam String newProfileImg) {
		// JWT에서 이메일 추출
		Optional<String> emailOpt = jwtService.extractEmailFromAccessToken(request);
		if (!emailOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body("유효하지않거나 만료된 토큰입니다.");
		}

		// 사용자 정보 찾기 및 프로필사진 업데이트
		String email = emailOpt.get();
		MemberTemp member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new EntityNotFoundException("해당이메일은 가입되지않은 이메일입니다: " + email));

		member.updateProfileImg(newProfileImg);
		memberRepository.save(member);

		return ResponseEntity.ok("프로필사진 변경완료.");
	}

}
