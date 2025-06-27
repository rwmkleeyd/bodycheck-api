package com.eyebody.bodycheck_api.community.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.eyebody.bodycheck_api.community.domain.model.Board;

@Repository
public interface BoardJpaRepository extends JpaRepository<Board, Long>, JpaSpecificationExecutor<Board> {
}