package com.eyebody.bodycheck_api.chat.adapter.in.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eyebody.bodycheck_api.chat.adapter.in.rest.dto.req.ChatMessageRequest;
import com.eyebody.bodycheck_api.chat.adapter.in.rest.dto.res.ChatMessageResponse;
import com.eyebody.bodycheck_api.chat.application.port.ChatMessageUseCase;
import com.eyebody.bodycheck_api.chat.domain.model.ChatMessage;
import com.eyebody.bodycheck_api.common.security.SecurityUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatMessageController {

	private final ChatMessageUseCase chatMessageUseCase;

	/** ── DTO ↔ 엔티티 변환 ───────────────────────────────── */
	private ChatMessageResponse toResponse(ChatMessage e) {
		return new ChatMessageResponse(
			e.getId(), e.getUser().getId(), e.getSessionId(),
			e.getRole(), e.getContent(), e.getCreatedAt()
		);
	}

	/** 메시지 저장 (user role) */
	@PostMapping("/history")
	public ResponseEntity<ChatMessageResponse> saveMessage(@RequestBody ChatMessageRequest req) {
		Long userId = SecurityUtils.currentUserId();
		ChatMessage saved = chatMessageUseCase.saveUserMessage(
			userId, req.sessionId(), req.content()
		);
		return ResponseEntity.ok(toResponse(saved));
	}

	/** 특정 세션의 최근 N개 메시지 조회 */
	@GetMapping("/history")
	public ResponseEntity<List<ChatMessageResponse>> loadHistory(
		@RequestParam String sessionId,
		@RequestParam(defaultValue = "20") int limit
	) {
		Long userId = SecurityUtils.currentUserId();
		List<ChatMessageResponse> list = chatMessageUseCase
			.recentMessages(userId, sessionId, limit)
			.stream().map(this::toResponse)
			.collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}
}