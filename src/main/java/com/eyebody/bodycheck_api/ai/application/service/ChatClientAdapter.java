package com.eyebody.bodycheck_api.ai.application.service;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.eyebody.bodycheck_api.ai.adapter.in.rest.dto.req.ChatRequest;
import com.eyebody.bodycheck_api.ai.adapter.in.rest.dto.res.ChatResponse;

import reactor.core.publisher.Mono;

@Component
public class ChatClientAdapter {

    private final WebClient aiWebClient;

    public ChatClientAdapter(WebClient aiWebClient) {
        this.aiWebClient = aiWebClient;
    }

    public Mono<ChatResponse> sendChatRequest(ChatRequest request) {
        return aiWebClient.post()
            .uri("/v1/chat")
            .bodyValue(request)
            .retrieve()
            .bodyToMono(ChatResponse.class);
    }

}
