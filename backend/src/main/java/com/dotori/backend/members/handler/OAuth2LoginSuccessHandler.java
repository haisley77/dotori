package com.dotori.backend.members.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.dotori.backend.members.CustomOAuth2User;
import com.dotori.backend.members.jwt.service.JwtService;
import com.dotori.backend.members.redis.RedisService;
import com.dotori.backend.members.type.Role;

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

			// User의 Role이 GUEST일 경우 처음 요청한 회원이므로 회원가입 페이지로 리다이렉트인데 우리는 모두 USER
			if (oAuth2User.getRole() == Role.GUEST) {
				// String accessToken = jwtService.createAccessToken(oAuth2User.getEmail());
				// response.addHeader(jwtService.getAccessHeader(), "Bearer " + accessToken);
				// response.sendRedirect("oauth2/sign-up"); // 프론트의 회원가입 추가 정보 입력 폼으로 리다이렉트

				// jwtService.sendAccessToken(response, accessToken);
			} else {
				loginSuccess(response, oAuth2User); // 로그인에 성공한 경우 access, refresh 토큰 생성
			}
		} catch (Exception e) {
			throw e;
		}

	}

	private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {
		String accessToken = jwtService.createAccessToken(oAuth2User.getEmail());
		String refreshToken = jwtService.createAndStoreRefreshToken(oAuth2User.getEmail());

		Cookie cookie = new Cookie("accessToken", accessToken);
		cookie.setHttpOnly(true); //HttpOnly 플래그 JavaScript를 포함한 클라이언트 측 스크립트로부터 접근x
		cookie.setSecure(true); // Secure 플래그 HTTPS에서만 쿠키 전송
		cookie.setPath("/"); // 쿠키 경로 설정
		response.addCookie(cookie);

		response.addHeader(jwtService.getAccessHeader(), "Bearer " + accessToken);

		// jwtService.sendAccessToken(response, accessToken);
		jwtService.updateRefreshToken(oAuth2User.getEmail(), refreshToken);
	}
}