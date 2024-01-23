package com.dotori.backend.members.controller;

import com.dotori.backend.members.dto.SocialLoginRequest;
import com.dotori.backend.members.dto.LoginResponse;
import com.dotori.backend.members.dto.MemberResponse;
import com.dotori.backend.members.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class memberController {
    private final MemberService memberService;

    @PostMapping("/social-login")
    public ResponseEntity<LoginResponse> doSocialLogin(@RequestBody @Valid SocialLoginRequest request) {

        return ResponseEntity.created(URI.create("/social-login"))
                .body(memberService.doSocialLogin(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                memberService.getMember(id)
        );
    }
}
