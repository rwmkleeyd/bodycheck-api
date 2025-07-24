package com.eyebody.bodycheck_api.chat.domain.port;

public interface LlmClient {
	String chat(String prompt);
}