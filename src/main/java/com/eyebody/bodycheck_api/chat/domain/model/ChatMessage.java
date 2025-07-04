package com.eyebody.bodycheck_api.chat.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
	name = "chat_messages",
	indexes = {
		@Index(name = "idx_chat_user_created", columnList = "userId, createdAt"),
		@Index(name = "idx_chat_session", columnList = "sessionId")
	}
)
public class ChatMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** JWT sub 또는 회원 PK */
	@Column(nullable = false)
	private Long userId;

	/** (옵션) 프런트가 유지하는 세션 키 ─ 동일 세션의 최근 N개를 컨텍스트로 넣음 */
	@Column(length = 64, nullable = false)
	private String sessionId;

	/** user / assistant 구분(LLaMA 프롬프트 role) */
	@Column(length = 16, nullable = false)
	private String role;

	/** 실제 메세지 내용(UTF-8) */
	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;

	/** 생성 시각(기본값 = now) */
	@Column(nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	// ── 기본 생성자 & getter/setter ───────────────────────────────
	public ChatMessage(Long userId, String sessionId,
		String role, String content) {
		this.userId = userId;
		this.sessionId = sessionId;
		this.role = role;
		this.content = content;
	}
}
