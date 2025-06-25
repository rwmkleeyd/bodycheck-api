package com.eyebody.bodycheck_api.community.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.eyebody.bodycheck_api.community.domain.model.Board;

public interface BoardJpaRepository extends JpaRepository<Board, Long>, JpaSpecificationExecutor<Board> {
}