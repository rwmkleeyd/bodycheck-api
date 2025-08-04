package com.eyebody.bodycheck_api.chat.adapter.in.rest.dto.res;

/** LLaMA2가 최종 내려준 답변 1개 */
public record ChatCompletionResponse(
	String content   // assistant 응답 텍스트
) {}