package com.eyebody.bodycheck_api.user.adapter.in.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyebody.bodycheck_api.community.adapter.in.rest.dto.request.LoginRequest;
import com.eyebody.bodycheck_api.community.adapter.in.rest.dto.request.SignUpRequest;
import com.eyebody.bodycheck_api.community.adapter.in.rest.dto.response.TokenResponse;
import com.eyebody.bodycheck_api.community.application.usecase.AuthUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {
	private final AuthUseCase authUseCase;

	@PostMapping("/signup")
	public ResponseEntity<TokenResponse> signup(@RequestBody @Valid SignUpRequest req) {
		String token = authUseCase.signup(req.email(), req.password(), req.nickname());
		return ResponseEntity.ok(new TokenResponse(token));
	}

	@PostMapping("/login")
	public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest req) {
		String token = authUseCase.login(req.email(), req.password());
		return ResponseEntity.ok(new TokenResponse(token));
	}
}
