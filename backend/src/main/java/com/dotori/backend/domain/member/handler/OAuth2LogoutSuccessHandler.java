package com.dotori.backend.domain.member.handler;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.dotori.backend.domain.member.jwt.service.JwtService;
import com.dotori.backend.domain.member.redis.RedisService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LogoutSuccessHandler implements LogoutSuccessHandler {

	private final JwtService jwtService;
	private final RedisService redisService;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException {
		Optional<String> accessTokenOpt = jwtService.extractAccessToken(request);

		accessTokenOpt.ifPresent(token -> {
			// 쿠키에서 accessToken 제거
			jwtService.removeAccessToken(response);

			// Email 추출 후 Redis에서 refreshToken 블랙리스트 처리
			jwtService.extractEmail(token).ifPresent(email -> {
				Optional<String> refreshTokenOpt = redisService.getRefreshToken(email);
				refreshTokenOpt.ifPresent(refreshToken -> {
					// 블랙리스트에 추가
					redisService.addToBlacklist(refreshToken);
					// Redis에서 refreshToken 제거
					redisService.removeRefreshToken(email);
				});
			});
		});

		// 클라이언트에 로그아웃 완료 응답
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print("로그아웃 완료");
		response.sendRedirect("/");
	}

}
