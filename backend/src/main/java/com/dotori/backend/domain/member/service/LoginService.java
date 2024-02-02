package com.dotori.backend.domain.member.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dotori.backend.domain.member.Repository.MemberRepository;
import com.dotori.backend.domain.member.model.MemberTemp;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		MemberTemp memberTemp = memberRepository.findByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException("해당 이메일이 존재하지 않습니다."));

		return org.springframework.security.core.userdetails.User.builder()
			.username(memberTemp.getEmail())
			.roles(memberTemp.getRole().name())
			.build();
	}
}