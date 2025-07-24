package com.eyebody.bodycheck_api.chat.adapter.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyebody.bodycheck_api.chat.adapter.rest.dto.req.ChatMessageRequest;
import com.eyebody.bodycheck_api.chat.adapter.rest.dto.res.ChatCompletionResponse;
import com.eyebody.bodycheck_api.chat.application.port.ChatInteractionUseCase;
import com.eyebody.bodycheck_api.common.security.SecurityUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatInteractionController {

	private final ChatInteractionUseCase chatInteractionUseCase;

	/** 질문 → 답변 */
	@PostMapping("/ask")
	public ResponseEntity<ChatCompletionResponse> ask(
		@RequestBody ChatMessageRequest req) {
		Long userId = SecurityUtils.currentUserId();     // 한 줄로 끝!

		ChatCompletionResponse res = chatInteractionUseCase
			.askLlama(userId, req.sessionId(), req.content());
		return ResponseEntity.ok(res);
	}
}