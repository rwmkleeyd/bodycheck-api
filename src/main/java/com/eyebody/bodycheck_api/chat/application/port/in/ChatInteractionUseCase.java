package com.eyebody.bodycheck_api.chat.application.port.in;

import com.eyebody.bodycheck_api.chat.adapter.in.rest.dto.res.ChatCompletionResponse;

/**
 * "질문 → 프롬프트 조립 → LLaMA 호출 → 로그 저장" 전체 플로우
 */
public interface ChatInteractionUseCase {

	ChatCompletionResponse askLlama(
		Long userId,
		String sessionId,
		String content     // 사용자가 보낸 문장
	);
}