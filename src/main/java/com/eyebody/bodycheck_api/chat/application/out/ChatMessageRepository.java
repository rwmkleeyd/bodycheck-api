package com.eyebody.bodycheck_api.chat.application.out;

import java.util.List;

import com.eyebody.bodycheck_api.chat.domain.model.ChatMessage;

public interface ChatMessageRepository {

	ChatMessage save(ChatMessage message);

	List<ChatMessage> findRecentByUserAndSession(Long userId, String sessionId, int limit);
}
