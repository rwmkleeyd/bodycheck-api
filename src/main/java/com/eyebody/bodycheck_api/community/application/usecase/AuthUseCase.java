package com.eyebody.bodycheck_api.community.application.usecase;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eyebody.bodycheck_api.common.security.JwtTokenProvider;
import com.eyebody.bodycheck_api.community.infra.persistence.UserJpaRepository;
import com.eyebody.bodycheck_api.user.domain.model.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AuthUseCase {
	private final UserJpaRepository userJpaRepository;
	private final PasswordEncoder encoder;
	private final JwtTokenProvider jwt;

	@Transactional
	public String signup(String email, String rawPw, String nick) {
		assert email != null && rawPw != null && nick != null;
		userJpaRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("already exists"));
		User saved = userJpaRepository.save(User.createEntity(email, encoder.encode(rawPw), nick));
		return jwt.generate(saved.getId(), saved.getRole().name());
	}

	public String login(String email, String rawPw) {
		assert email != null && rawPw != null;
		User user = userJpaRepository.findByEmail(email)
			.orElseThrow(() -> new RuntimeException("no user"));
		if (!encoder.matches(rawPw, user.getPassword()))
			throw new RuntimeException("bad pw");
		return jwt.generate(user.getId(), user.getRole().name());
	}
}
