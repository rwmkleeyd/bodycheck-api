package com.eyebody.bodycheck_api.community.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.eyebody.bodycheck_api.community.domain.model.Board;
import com.eyebody.bodycheck_api.community.application.out.BoardRepository;

// 구현체가 사용할 인터페이스를 알고 있으면 됨
@Repository
public interface BoardJpaRepository
	extends JpaRepository<Board, Long>, JpaSpecificationExecutor<Board>, BoardRepository {
}