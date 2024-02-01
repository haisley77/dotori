package com.dotori.backend.members.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dotori.backend.members.dto.LoginResponse;
import com.dotori.backend.members.dto.MemberResponse;
import com.dotori.backend.members.dto.SocialLoginRequest;
import com.dotori.backend.members.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/members")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class memberController {
	private final MemberService memberService;

	@PostMapping("/social-login")
	public ResponseEntity<LoginResponse> doSocialLogin(@RequestBody @Valid SocialLoginRequest request) {
		System.out.println(request);
		return ResponseEntity.created(URI.create("/social-login"))
			.body(memberService.doSocialLogin(request));
	}

	@GetMapping("/{phoneNum}")
	public ResponseEntity<MemberResponse> getUser(@PathVariable("phoneNum") String phoneNum) {
		return ResponseEntity.ok(
			memberService.getMember(phoneNum)
		);
	}
}
