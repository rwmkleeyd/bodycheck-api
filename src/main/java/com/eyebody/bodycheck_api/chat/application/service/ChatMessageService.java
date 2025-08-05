package com.eyebody.bodycheck_api.chat.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eyebody.bodycheck_api.chat.application.port.in.ChatMessageUseCase;
import com.eyebody.bodycheck_api.chat.domain.model.ChatMessage;
import com.eyebody.bodycheck_api.chat.application.port.out.ChatMessageRepository;

@Service
@Transactional
public class ChatMessageService implements ChatMessageUseCase {

	private final ChatMessageRepository repo;

	public ChatMessageService(ChatMessageRepository repo) {
		this.repo = repo;
	}

	@Override
	public void saveUserMessage(Long userId, String sessionId, String content) {
		repo.save(ChatMessage.user(userId, sessionId, content));
	}

	@Override
	public void saveAssistantMessage(Long userId, String sessionId, String content) {
		repo.save(ChatMessage.assistant(userId, sessionId, content));
	}

	@Override
	public List<ChatMessage> recentMessages(Long userId, String sessionId, int limit) {
		return repo.findRecent(userId, sessionId, limit);
	}
}