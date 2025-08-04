package com.eyebody.bodycheck_api.user.adapter.in.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyebody.bodycheck_api.user.adapter.in.rest.dto.req.LoginRequest;
import com.eyebody.bodycheck_api.user.adapter.in.rest.dto.req.SignUpRequest;
import com.eyebody.bodycheck_api.user.adapter.in.rest.dto.res.TokenResponse;
import com.eyebody.bodycheck_api.user.application.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {
	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<TokenResponse> signup(@RequestBody @Valid SignUpRequest req) {
		String token = authService.signup(req.email(), req.password(), req.nickname());
		return ResponseEntity.ok(new TokenResponse(token));
	}

	@PostMapping("/login")
	public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest req) {
		String token = authService.login(req.email(), req.password());
		return ResponseEntity.ok(new TokenResponse(token));
	}
}
