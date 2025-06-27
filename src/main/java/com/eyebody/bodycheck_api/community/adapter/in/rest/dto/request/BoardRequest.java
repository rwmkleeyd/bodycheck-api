package com.eyebody.bodycheck_api.community.adapter.in.rest.dto.request;

import jakarta.validation.constraints.NotBlank;

public record BoardRequest(
	@NotBlank String name,
	@NotBlank String description
) {}
