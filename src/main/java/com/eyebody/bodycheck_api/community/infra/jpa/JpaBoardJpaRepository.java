package com.eyebody.bodycheck_api.community.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.eyebody.bodycheck_api.community.domain.model.Board;
import com.eyebody.bodycheck_api.community.domain.repository.BoardRepository;

@Repository
public interface JpaBoardJpaRepository
	extends JpaRepository<Board, Long>, JpaSpecificationExecutor<Board>, BoardRepository {
	// JpaRepository와 JpaSpecificationExecutor를 상속받아 기본 CRUD 및 스펙 기반 쿼리 기능을 제공
	// 추가적인 메서드는 필요에 따라 정의할 수 있습니다.
}