package com.eyebody.bodycheck_api.chat.application.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eyebody.bodycheck_api.chat.adapter.in.rest.dto.res.ChatCompletionResponse;
import com.eyebody.bodycheck_api.chat.application.port.in.ChatInteractionUseCase;
import com.eyebody.bodycheck_api.chat.application.port.in.ChatMessageUseCase;
import com.eyebody.bodycheck_api.chat.domain.manager.PromptBuilder;
import com.eyebody.bodycheck_api.chat.domain.model.ChatMessage;
import com.eyebody.bodycheck_api.chat.application.port.out.LlmClient;

/** 대화 전체 흐름을 담당하는 Use‑Case 구현체 */
@Service
@Transactional
public class ChatInteractionService implements ChatInteractionUseCase {

	private final ChatMessageUseCase msgSvc; // 다른 Use‑Case 재사용
	private final LlmClient llm;             // 도메인 Port
	private final int historyLimit;

	public ChatInteractionService(
		ChatMessageUseCase msgSvc,
		LlmClient llm,
		@Value("${llama.history-limit:20}") int historyLimit) {

		this.msgSvc = msgSvc;
		this.llm = llm;
		this.historyLimit = historyLimit;
	}

	@Override
	public ChatCompletionResponse askLlama(
		Long userId, String sessionId, String content) {

		/* 1) 사용자 메시지 저장 */
		msgSvc.saveUserMessage(userId, sessionId, content);

		/* 2) 최근 N개 기록 조회 */
		List<ChatMessage> history =
			msgSvc.recentMessages(userId, sessionId, historyLimit);
		Collections.reverse(history);

		/* 3) 프롬프트 생성 */
		String prompt = PromptBuilder.build(history);

		/* 4) LLM 호출 (Port) */
		String assistantText = llm.chat(prompt);

		/* 5) assistant 메시지 저장 */
		msgSvc.saveAssistantMessage(userId, sessionId, assistantText);

		/* 6) 컨트롤러로 응답 */
		return new ChatCompletionResponse(assistantText);
	}
}