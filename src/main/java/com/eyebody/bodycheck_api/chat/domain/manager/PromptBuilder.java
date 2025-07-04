package com.eyebody.bodycheck_api.chat.domain.manager;

import java.util.List;
import java.util.stream.Collectors;

import com.eyebody.bodycheck_api.chat.domain.model.ChatMessage;

/** 최근 메시지들을 llama.cpp JSON 배열 형식으로 직렬화 */
public class PromptBuilder {

	private static final String SYSTEM_TXT =
		"You are a fitness & diet expert chatbot. Reply in Korean.";

	public static String build(List<ChatMessage> history) {

		String sys = String.format(
			"{\"role\":\"system\",\"content\":\"%s\"}", SYSTEM_TXT);

		String msgs = history.stream()
			.map(m -> String.format(
				"{\"role\":\"%s\",\"content\":\"%s\"}",
				m.getRole(), escape(m.getContent())))
			.collect(Collectors.joining(","));

		return "[" + sys + "," + msgs + "]";
	}

	private static String escape(String s) {
		return s.replace("\"", "\\\"");
	}
}