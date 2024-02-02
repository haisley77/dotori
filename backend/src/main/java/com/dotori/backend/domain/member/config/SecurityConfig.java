package com.dotori.backend.domain.member.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.dotori.backend.domain.member.handler.OAuth2LoginFailureHandler;
import com.dotori.backend.domain.member.handler.OAuth2LoginSuccessHandler;
import com.dotori.backend.domain.member.jwt.filter.JwtAuthenticationProcessingFilter;
import com.dotori.backend.domain.member.jwt.service.JwtService;
import com.dotori.backend.domain.member.redis.RedisService;
import com.dotori.backend.domain.member.repository.MemberRepository;
import com.dotori.backend.domain.member.service.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;

/**
 * 인증은 CustomJsonUsernamePasswordAuthenticationFilter에서 authenticate()로 인증된 사용자로 처리
 * JwtAuthenticationProcessingFilter는 AccessToken, RefreshToken 재발급
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtService jwtService;
	private final MemberRepository memberRepository;
	private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
	private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
	private final CustomOAuth2UserService customOAuth2UserService;
	private final LogoutSuccessHandler OAuth2LogoutSuccessHandler;
	private final RedisService redisService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
			.formLogin()
			.disable() // FormLogin 사용 X
			.httpBasic()
			.disable() // httpBasic 사용 X
			.csrf()
			.disable() // csrf 보안 사용 X
			// .headers()
			// .frameOptions()
			// .disable()
			// .and()

			//
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)

			.and()

			//== URL별 권한 관리 옵션 ==//
			.authorizeRequests()
			// 기본 페이지, css, image, js 하위 폴더에 있는 자료들은 모두 접근 가능
			.antMatchers("/",
				"/css/**", "/images/**", "/js/**",
				"/favicon.ico", "/resources/**", "/static/**")
			.permitAll()

			.antMatchers("/**")
			.permitAll() // 회원가입 접근 가능

			.anyRequest()
			.authenticated() // 위의 경로 이외에는 모두 인증된 사용자만 접근 가능
			.and()
			.logout()
			.logoutUrl("/logout") // 로그아웃 URL 지정
			.logoutSuccessHandler(OAuth2LogoutSuccessHandler)
			.invalidateHttpSession(true) // 세션 무효화
			.deleteCookies("JSESSIONID") // 쿠키 삭제

			.and()
			//== 소셜 로그인 설정 ==//
			.oauth2Login()
			.successHandler(oAuth2LoginSuccessHandler) // 성공시 Handler 설정
			.failureHandler(oAuth2LoginFailureHandler) // 소셜 로그인 실패 시 핸들러 설정
			.userInfoEndpoint()
			.userService(customOAuth2UserService);// customUserService 설정

		// 원래 스프링 시큐리티 필터 순서가 LogoutFilter 이후에 로그인 필터 동작
		// 순서 : LogoutFilter -> JwtAuthenticationProcessingFilter -> CustomJsonUsernamePasswordAuthenticationFilter

		return http.build();
	}

	/**
	 * AuthenticationManager 설정 후 등록
	 * PasswordEncoder를 사용하는 AuthenticationProvider 지정 (PasswordEncoder는 위에서 등록한 PasswordEncoder 사용)
	 * FormLogin(기존 스프링 시큐리티 로그인)과 동일하게 DaoAuthenticationProvider 사용
	 * UserDetailsService는 커스텀 LoginService로 등록
	 * 또한, FormLogin과 동일하게 AuthenticationManager로는 구현체인 ProviderManager 사용(return ProviderManager)
	 *
	 */

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("*"); // 모든 도메인에서 접근 허용 (실제 운영 환경에서는 보안 상의 이유로 특정 도메인만 허용해야 함)
		configuration.addAllowedMethod("*"); // 모든 HTTP 메소드 허용
		configuration.addAllowedHeader("*"); // 모든 헤더 허용
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter() {
		JwtAuthenticationProcessingFilter jwtAuthenticationFilter = new JwtAuthenticationProcessingFilter(jwtService,
			memberRepository, redisService);
		return jwtAuthenticationFilter;
	}

}