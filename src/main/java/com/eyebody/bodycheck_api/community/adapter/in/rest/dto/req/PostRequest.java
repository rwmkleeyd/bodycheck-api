package com.eyebody.bodycheck_api.community.adapter.in.rest.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PostRequest(
	@NotBlank @Size(max = 100) String title,
	@NotBlank String content,
	@NotNull Long authorId
) {}