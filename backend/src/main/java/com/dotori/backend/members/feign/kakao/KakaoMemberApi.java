package com.dotori.backend.members.feign.kakao;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.dotori.config.FeignConfiguration;

@FeignClient(value = "kakaoMember", url = "https://kapi.kakao.com", configuration = {FeignConfiguration.class})
public interface KakaoMemberApi {
	@GetMapping("/v2/user/me")
	ResponseEntity<String> getMemberInfo(@RequestHeader Map<String, String> header);
}