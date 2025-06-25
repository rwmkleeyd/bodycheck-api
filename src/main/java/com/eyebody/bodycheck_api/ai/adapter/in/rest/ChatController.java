package com.eyebody.bodycheck_api.ai.adapter.in.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyebody.bodycheck_api.ai.adapter.in.rest.dto.ChatRequest;
import com.eyebody.bodycheck_api.ai.adapter.in.rest.dto.ChatResponse;
import com.eyebody.bodycheck_api.ai.application.service.ChatClientAdapter;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatClientAdapter chatClientAdapter;

    public ChatController(ChatClientAdapter chatClientAdapter) {
        this.chatClientAdapter = chatClientAdapter;
    }

    @PostMapping("")
    public ResponseEntity<Mono<ChatResponse>> chat(@Valid @RequestBody ChatRequest request) {
        // Forward the request to the AI service via the adapter
        Mono<ChatResponse> response = chatClientAdapter.sendChatRequest(request);
        return ResponseEntity.ok(response);
    }
}
