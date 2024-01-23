package com.dotori.backend.members.feign.kakao;


import com.dotori.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(value = "kakaoMember", url="https://kapi.kakao.com", configuration = {FeignConfiguration.class})
public interface KakaoMemberApi {
    @GetMapping("/members/mypage")
    ResponseEntity<String> getMemberInfo(@RequestHeader Map<String, String> header);
}