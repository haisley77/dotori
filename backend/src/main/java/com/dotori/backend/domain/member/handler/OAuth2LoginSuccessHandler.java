package com.dotori.backend.domain.member.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.dotori.backend.domain.member.CustomOAuth2User;
import com.dotori.backend.domain.member.jwt.service.JwtService;
import com.dotori.backend.domain.member.redis.RedisService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
//@Transactional
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

	private final JwtService jwtService;
	private RedisService redisService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		log.info("OAuth2 Login 성공!");
		try {
			CustomOAuth2User oAuth2User = (CustomOAuth2User)authentication.getPrincipal();
			loginSuccess(response, oAuth2User); // 로그인에 성공한 경우 access, refresh 토큰 생성
		} catch (Exception e) {
			throw e;
		}

	}

	private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {
		//토큰2개생성
		String accessToken = jwtService.createAccessToken(oAuth2User.getEmail());
		String refreshToken = jwtService.createAndStoreRefreshToken(oAuth2User.getEmail());

		//로그인성공하고 쿠키생성부분
		Cookie cookie = new Cookie("accessToken", accessToken);
		cookie.setHttpOnly(true); //HttpOnly 플래그 JavaScript를 포함한 클라이언트 측 스크립트로부터 접근x
		cookie.setSecure(true); // Secure 플래그 HTTPS에서만 쿠키 전송
		cookie.setPath("/"); // 쿠키 경로 설정
		response.addCookie(cookie);

		// jwtService.sendAccessToken(response, accessToken);
		jwtService.updateRefreshToken(oAuth2User.getEmail(), refreshToken);
		response.sendRedirect("http://localhost:9000");
	}
}