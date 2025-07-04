package com.eyebody.bodycheck_api.chat.adapter.rest.dto.res;

import java.time.LocalDateTime;

public record ChatMessageResponse(
	Long id,
	Long userId,
	String sessionId,
	String role,          // "user" | "assistant"
	String content,
	LocalDateTime createdAt
) {}