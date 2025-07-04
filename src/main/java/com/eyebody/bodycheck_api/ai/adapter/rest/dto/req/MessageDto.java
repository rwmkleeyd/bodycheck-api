package com.eyebody.bodycheck_api.ai.adapter.rest.dto.req;

import jakarta.validation.constraints.NotBlank;

public record MessageDto(
	@NotBlank String role,
	@NotBlank String content
) {}