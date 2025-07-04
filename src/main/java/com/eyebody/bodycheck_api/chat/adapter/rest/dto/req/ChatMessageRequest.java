package com.eyebody.bodycheck_api.chat.adapter.rest.dto.req;

public record ChatMessageRequest(
	String sessionId,   // 동일 세션(채팅창) 식별자
	String content      // 사용자가 입력한 프롬프트
) {}