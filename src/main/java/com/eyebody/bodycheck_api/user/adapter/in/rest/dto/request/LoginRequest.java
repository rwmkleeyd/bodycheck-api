package com.eyebody.bodycheck_api.user.adapter.in.rest.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
	@Email @NotBlank String email,
	@NotBlank String password
	) {}