package com.dotori.backend.domain.member.config;

import org.apache.hc.core5.http.HttpHeaders;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry corsRegistry) {
		corsRegistry.addMapping("/**")
			.allowedOrigins("http://localhost:9000")
			.allowedHeaders("*")
			.allowCredentials(true) //쿠키허용
			.allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE")
			.exposedHeaders(HttpHeaders.LOCATION, HttpHeaders.SET_COOKIE);

	}
}