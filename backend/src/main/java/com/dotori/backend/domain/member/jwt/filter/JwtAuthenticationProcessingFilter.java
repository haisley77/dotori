package com.dotori.backend.domain.member.jwt.filter;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dotori.backend.domain.member.Repository.MemberRepository;
import com.dotori.backend.domain.member.jwt.service.JwtService;
import com.dotori.backend.domain.member.model.Member;
import com.dotori.backend.domain.member.redis.RedisService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Jwt 인증 필터
 * "/login" 이외의 URI 요청이 왔을 때 처리하는 필터
 *
 * 기본적으로 사용자는 요청 헤더에 AccessToken만 담아서 요청
 * AccessToken 만료 시에만 RefreshToken을 요청 헤더에 AccessToken과 함께 요청
 *
 * 1. RefreshToken이 없고, AccessToken이 유효한 경우 -> 인증 성공 처리, RefreshToken을 재발급하지는 않는다.
 * 2. RefreshToken이 없고, AccessToken이 없거나 유효하지 않은 경우 -> 인증 실패 처리, 403 ERROR
 * 3. RefreshToken이 있는 경우 -> DB의 RefreshToken과 비교하여 일치하면 AccessToken 재발급, RefreshToken 재발급(RTR 방식)
 *                              인증 성공 처리는 하지 않고 실패 처리
 *
 */
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {

	private static final String NO_CHECK_URL = "/login/*"; // "/login"으로 들어오는 요청은 Filter 작동 X

	private final JwtService jwtService;
	private final MemberRepository memberRepository;

	private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
	private RedisService redisService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		log.info("경로확인로그: {}", request.getRequestURI());
		if (request.getRequestURI().equals("/favicon.ico")) {
			return;
		}
		if (request.getRequestURI().equals(NO_CHECK_URL)) {
			filterChain.doFilter(request, response); // "/login" 요청이 들어오면, 다음 필터 호출
			return; // return으로 이후 현재 필터 진행 막기 (안해주면 아래로 내려가서 계속 필터 진행시킴)
		}

		// -> RefreshToken이 없거나 유효하지 않다면(DB에 저장된 RefreshToken과 다르다면) null을 반환
		// redis에 RefreshToken이 있는 경우는, AccessToken이 만료되어 요청한 경우밖에 없다.
		// 따라서, 위의 경우를 제외하면 추출한 refreshToken은 모두 null
		// 액세스 토큰에서 이메일 추출
		Optional<String> email = jwtService.extractEmailFromAccessToken(request);

		// 이메일을 기반으로 Redis에서 리프레쉬 토큰 가져오기
		String refreshToken = email.flatMap(redisService::getRefreshToken)
			.filter(jwtService::isTokenValid)
			.orElse(null);

		// 리프레시 토큰이 reids에 존재했다면, 사용자가 AccessToken이 만료되어서
		// RefreshToken까지 보낸 것이므로 리프레시 토큰이 DB의 리프레시 토큰과 일치하는지 판단 후,
		// 일치한다면 AccessToken을 재발급해준다.
		if (refreshToken != null) {
			checkRefreshTokenAndReIssueAccessToken(response, refreshToken);
			return; // RefreshToken을 보낸 경우에는 AccessToken을 재발급 하고 인증 처리는 하지 않게 하기위해 바로 return으로 필터 진행 막기
		}

		// RefreshToken이 없거나 유효하지 않다면, AccessToken을 검사하고 인증을 처리하는 로직 수행
		// AccessToken이 없거나 유효하지 않다면, 인증 객체가 담기지 않은 상태로 다음 필터로 넘어가기 때문에 403 에러 발생
		// AccessToken이 유효하다면, 인증 객체가 담긴 상태로 다음 필터로 넘어가기 때문에 인증 성공
		if (refreshToken == null) {
			checkAccessTokenAndAuthentication(request, response, filterChain);
		}
	}

	/**
	 *  [리프레시 토큰으로 유저 정보 찾기 & 액세스 토큰/리프레시 토큰 재발급 메소드]
	 *  파라미터로 들어온 헤더에서 추출한 리프레시 토큰으로 DB에서 유저를 찾고, 해당 유저가 있다면
	 *  JwtService.createAccessToken()으로 AccessToken 생성,
	 *  reIssueRefreshToken()로 리프레시 토큰 재발급 & DB에 리프레시 토큰 업데이트 메소드 호출
	 *  그 후 JwtService.sendAccessTokenAndRefreshToken()으로 응답 헤더에 보내기
	 */
	public void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, String refreshToken) {
		memberRepository.findByRefreshToken(refreshToken)
			.ifPresent(user -> {
				String reIssuedRefreshToken = reIssueRefreshToken(user);
				jwtService.sendAccessToken(response, jwtService.createAccessToken(user.getEmail()));
			});
	}

	/**
	 * [리프레시 토큰 재발급 & Redis에 리프레시 토큰 업데이트 메소드]
	 * jwtService.createRefreshToken()으로 리프레시 토큰 재발급 후
	 * Redis에 재발급한 리프레시 토큰 업데이트
	 */
	private String reIssueRefreshToken(Member member) {
		String reIssuedRefreshToken = jwtService.createRefreshToken();
		redisService.saveRefreshToken(member.getEmail(), reIssuedRefreshToken,
			jwtService.getRefreshTokenExpirationPeriod(), TimeUnit.MILLISECONDS);
		return reIssuedRefreshToken;
	}

	/**
	 * [액세스 토큰 체크 & 인증 처리 메소드]
	 * request에서 extractAccessToken()으로 액세스 토큰 추출 후, isTokenValid()로 유효한 토큰인지 검증
	 * 유효한 토큰이면, 액세스 토큰에서 extractEmail로 Email을 추출한 후 findByEmail()로 해당 이메일을 사용하는 유저 객체 반환
	 * 그 유저 객체를 saveAuthentication()으로 인증 처리하여
	 * 인증 허가 처리된 객체를 SecurityContextHolder에 담기
	 * 그 후 다음 인증 필터로 진행
	 */
	public void checkAccessTokenAndAuthentication(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		log.info("checkAccessTokenAndAuthentication() 호출");
		jwtService.extractAccessToken(request)
			.filter(jwtService::isTokenValid)
			.ifPresent(accessToken -> jwtService.extractEmail(accessToken)
				.ifPresent(email -> memberRepository.findByEmail(email)
				));

		filterChain.doFilter(request, response);
	}

}