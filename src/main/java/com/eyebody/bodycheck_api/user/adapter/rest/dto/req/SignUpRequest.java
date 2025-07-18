package com.eyebody.bodycheck_api.user.adapter.rest.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
	@Email @NotBlank String email,
	@Size(min = 6) String password,
	@NotBlank String nickname
	) {}