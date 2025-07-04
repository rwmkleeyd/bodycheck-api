package com.eyebody.bodycheck_api.user.adapter.rest.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
	@Email @NotBlank String email,
	@NotBlank String password
	) {}