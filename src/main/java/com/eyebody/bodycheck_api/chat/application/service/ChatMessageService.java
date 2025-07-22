package com.eyebody.bodycheck_api.chat.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eyebody.bodycheck_api.chat.application.in.ChatMessageUseCase;
import com.eyebody.bodycheck_api.chat.application.out.ChatMessageRepository;
import com.eyebody.bodycheck_api.chat.domain.manager.ChatMessageManager;
import com.eyebody.bodycheck_api.chat.domain.model.ChatMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatMessageService implements ChatMessageUseCase {

	private final ChatMessageRepository chatMessageRepository;
	private final ChatMessageManager chatMessageManager;

	/** ── 저장 로직 ─────────────────────────────────────────── */
	@Override
	@Transactional
	public ChatMessage saveUserMessage(Long userId, String sessionId, String content) {
		return chatMessageRepository.save(
			new ChatMessage(userId, "user", content)
		);
	}

	@Override
	@Transactional
	public ChatMessage saveAssistantMessage(Long userId, String sessionId, String content) {
		return chatMessageRepository.save(
			new ChatMessage(userId,"assistant", content)
		);
	}

	/** ── 조회 로직 ─────────────────────────────────────────── */
	@Override
	public List<ChatMessage> recentMessages(Long userId, String sessionId, int limit) {
		return chatMessageRepository.findRecentByUserAndSession(userId, sessionId, limit);
	}
}