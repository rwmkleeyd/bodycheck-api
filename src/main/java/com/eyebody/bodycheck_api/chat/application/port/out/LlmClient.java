package com.eyebody.bodycheck_api.chat.application.port.out;

public interface LlmClient {
	String chat(String prompt);
}