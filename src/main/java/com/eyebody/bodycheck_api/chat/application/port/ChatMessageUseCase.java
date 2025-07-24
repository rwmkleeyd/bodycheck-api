package com.eyebody.bodycheck_api.chat.application.port;

import java.util.List;

import com.eyebody.bodycheck_api.chat.domain.model.ChatMessage;

public interface ChatMessageUseCase {

	/** 사용자 메시지 저장 (role = "user") */
	ChatMessage saveUserMessage(Long userId, String sessionId, String content);

	/** 어시스턴트(모델) 메시지 저장 (role = "assistant") */
	ChatMessage saveAssistantMessage(Long userId, String sessionId, String content);

	/**
	 * 특정 사용자·세션의 최근 N개 메시지 반환 (내림차순 정렬 후 최신순으로)
	 *
	 * @param limit 최대 반환 개수
	 */
	List<ChatMessage> recentMessages(Long userId, String sessionId, int limit);
}