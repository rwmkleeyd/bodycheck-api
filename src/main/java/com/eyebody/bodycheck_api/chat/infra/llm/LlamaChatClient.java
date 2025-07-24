package com.eyebody.bodycheck_api.chat.infra.llm;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.eyebody.bodycheck_api.chat.domain.port.LlmClient;

@Component
public class LlamaChatClient implements LlmClient {

	private final WebClient llama;

	public LlamaChatClient(@Value("${llama.base-url}") String baseUrl) {
		this.llama = WebClient.builder().baseUrl(baseUrl).build();
	}

	@Override
	public String chat(String prompt) {
		Map<?,?> res = llama.post()
			.uri("/v1/chat")
			.bodyValue(Map.of("messages", prompt))
			.retrieve()
			.bodyToMono(Map.class)
			.block();

		return ((Map<?,?>)((List<?>)res.get("choices")).get(0))
			.get("message").toString();
	}
}
