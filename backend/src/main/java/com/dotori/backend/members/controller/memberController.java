package com.dotori.backend.members.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/members")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class memberController {

	@GetMapping("/jwt-test")
	public String jwtTest() {
		return "jwtTest 요청 성공";
	}

	// private final MemberService memberService;
	//
	// @PostMapping("/social-login")
	// public ResponseEntity<LoginResponse> doSocialLogin(@RequestBody @Valid SocialLoginRequest request) {
	// 	System.out.println(request);
	// 	return ResponseEntity.created(URI.create("/social-login"))
	// 		.body(memberService.doSocialLogin(request));
	// }
	//
	// @GetMapping("/{email}")
	// public ResponseEntity<MemberResponse> getUser(@PathVariable("email") String email) {
	// 	return ResponseEntity.ok(
	// 		memberService.getMember(email)
	// 	);
	// }
}
