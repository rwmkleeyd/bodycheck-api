package com.eyebody.bodycheck_api.chat.infra.jpa;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.eyebody.bodycheck_api.chat.application.out.ChatMessageRepository;
import com.eyebody.bodycheck_api.chat.domain.model.ChatMessage;

@Repository
public interface ChatMessageJpaRepository
	extends JpaRepository<ChatMessage, Long>, JpaSpecificationExecutor<ChatMessage>, ChatMessageRepository {
	// JpaRepository: 기본 CRUD 메소드 제공
	// JpaSpecificationExecutor: 동적 쿼리 지원 (필요시)
	// ChatMessageRepository: 사용자 정의 메소드 추가 가능

	List<ChatMessage> findByUserIdAndSessionIdOrderByCreatedAtDesc(
		Long userId,
		String sessionId,
		Pageable pageable
	);
}