package com.eyebody.bodycheck_api.ai.adapter.in.rest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.eyebody.bodycheck_api.ai.adapter.in.rest.dto.ChatRequest;
import com.eyebody.bodycheck_api.ai.adapter.in.rest.dto.ChatResponse;
import com.eyebody.bodycheck_api.ai.adapter.in.rest.dto.MessageDto;
import com.eyebody.bodycheck_api.ai.application.service.ChatClientAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@WebMvcTest(ChatController.class)
@Import(ChatControllerTest.TestConfig.class)
public class ChatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ChatClientAdapter chatClientAdapter;

    @Configuration
    static class TestConfig {
        @Bean
        public ChatClientAdapter chatClientAdapter() {
            return mock(ChatClientAdapter.class);
        }
    }

    @Test
    public void testHelloEndpoint() throws Exception {
        // Given
        ChatResponse expectedResponse = new ChatResponse("안녕하세요! 어떻게 도와드릴까요?");
        when(chatClientAdapter.getSimpleGreeting()).thenReturn(expectedResponse);

        // When & Then
        mockMvc.perform(post("/api/chat/hello")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.reply").value("안녕하세요! 어떻게 도와드릴까요?"));
    }

    @Test
    public void testChatEndpoint() throws Exception {
        // Given
        MessageDto message = new MessageDto("user", "안녕?");
        ChatRequest request = new ChatRequest(List.of(message));
        ChatResponse expectedResponse = new ChatResponse("안녕하세요! 어떻게 도와드릴까요?");

        when(chatClientAdapter.sendChatRequest(request)).thenReturn(Mono.just(expectedResponse));

        // When & Then
        mockMvc.perform(post("/api/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
