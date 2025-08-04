package com.eyebody.bodycheck_api.ai.adapter.in.rest.dto.req;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

public record ChatRequest(
	@NotEmpty List<MessageDto> messages
) {}