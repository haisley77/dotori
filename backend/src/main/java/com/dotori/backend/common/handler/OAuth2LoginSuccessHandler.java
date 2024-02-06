package com.dotori.backend.common.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.dotori.backend.domain.member.model.dto.CustomOAuth2User;
import com.dotori.backend.domain.member.service.JwtService;
import com.dotori.backend.domain.member.service.RedisService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
//@Transactional
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

	private final JwtService jwtService;
	private final RedisService redisService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		log.info("OAuth2 Login 성공!");
		try {
			SecurityContextHolder.getContext().setAuthentication(authentication);
			log.info("authentication:{}", authentication);
			CustomOAuth2User oAuth2User = (CustomOAuth2User)authentication.getPrincipal();
			loginSuccess(response, oAuth2User); // 로그인에 성공한 경우 access, refresh 토큰 생성
		} catch (Exception e) {
			throw e;
		}

	}

	private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {
		//토큰2개생성
		String accessToken = jwtService.createAccessToken(oAuth2User.getEmail(), oAuth2User.getRole().name());
		String refreshToken = jwtService.createAndStoreRefreshToken(oAuth2User.getEmail());

		//쿠키생성
		jwtService.sendAccessToken(response, accessToken);
		jwtService.updateRefreshToken(oAuth2User.getEmail(), refreshToken);

		response.sendRedirect("http://localhost:9000");
	}
}