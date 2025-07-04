package com.eyebody.bodycheck_api.chat.application.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.eyebody.bodycheck_api.chat.adapter.rest.dto.res.ChatCompletionResponse;
import com.eyebody.bodycheck_api.chat.application.in.ChatInteractionUseCase;
import com.eyebody.bodycheck_api.chat.application.in.ChatMessageUseCase;
import com.eyebody.bodycheck_api.chat.domain.manager.PromptBuilder;
import com.eyebody.bodycheck_api.chat.domain.model.ChatMessage;

@Service
@Transactional
public class LlamaChatService implements ChatInteractionUseCase {

	private final ChatMessageUseCase msgSvc;
	private final WebClient llama;
	private final int historyLimit;

	public LlamaChatService(
		ChatMessageUseCase msgSvc,
		@Value("${llama.base-url}") String baseUrl,
		@Value("${llama.history-limit:20}") int historyLimit) {

		this.msgSvc = msgSvc;
		this.historyLimit = historyLimit;
		this.llama = WebClient.builder().baseUrl(baseUrl).build();
	}

	@Override
	public ChatCompletionResponse askLlama(
		Long userId, String sessionId, String content) {

		// 1) 사용자 메시지 먼저 저장
		msgSvc.saveUserMessage(userId, sessionId, content);

		// 2) 최근 N개 + 방금 메시지 가져와 시간순 정렬
		List<ChatMessage> history = msgSvc
			.recentMessages(userId, sessionId, historyLimit);
		java.util.Collections.reverse(history);

		// 3) 프롬프트 생성
		String prompt = PromptBuilder.build(history);

		// 4) FastAPI /v1/chat 호출
		Map<?,?> res = llama.post()
			.uri("/v1/chat")
			.bodyValue(Map.of("messages", prompt))
			.retrieve()
			.bodyToMono(Map.class)
			.block();

		// FastAPI 응답 형식에 맞게 추출 (필요시 수정)
		String assistantText =
			((Map<?,?>)((List<?>)res.get("choices")).get(0))
				.get("message").toString();

		// 5) assistant 메시지 저장
		msgSvc.saveAssistantMessage(userId, sessionId, assistantText);

		// 6) 최종 응답
		return new ChatCompletionResponse(assistantText);
	}
}