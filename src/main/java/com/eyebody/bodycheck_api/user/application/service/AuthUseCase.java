package com.eyebody.bodycheck_api.user.application.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eyebody.bodycheck_api.common.security.JwtTokenProvider;
import com.eyebody.bodycheck_api.user.application.in.UserRepository;
import com.eyebody.bodycheck_api.user.domain.model.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AuthUseCase {
	private final UserRepository userRepository;
	private final PasswordEncoder encoder;
	private final JwtTokenProvider jwt;

	@Transactional
	public String signup(String email, String rawPw, String nick) {
		assert email != null && rawPw != null && nick != null;
		userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("already exists")); // JPA 인프라 코드
		User saved = userRepository.save(User.createEntity(email, encoder.encode(rawPw), nick)); // JPA 인프라 코드
		return jwt.generate(saved.getId(), saved.getRole().name());
	}

	public String login(String email, String rawPw) {
		assert email != null && rawPw != null;
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new RuntimeException("no user"));
		if (!encoder.matches(rawPw, user.getPassword()))
			throw new RuntimeException("bad pw");
		return jwt.generate(user.getId(), user.getRole().name());
	}
}
