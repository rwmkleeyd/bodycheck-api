package com.eyebody.bodycheck_api.community.adapter.rest.dto.req;

import jakarta.validation.constraints.NotBlank;

public record BoardRequest(
	@NotBlank String name,
	@NotBlank String description
) {}
