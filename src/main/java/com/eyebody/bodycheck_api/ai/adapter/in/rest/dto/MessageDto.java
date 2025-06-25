package com.eyebody.bodycheck_api.ai.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record MessageDto(
	@NotBlank String role,
	@NotBlank String content
) {}