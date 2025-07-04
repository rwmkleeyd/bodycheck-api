package com.eyebody.bodycheck_api.community.infra.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.eyebody.bodycheck_api.community.domain.model.Comment;
import com.eyebody.bodycheck_api.community.application.out.CommentRepository;

@Repository
public interface CommentJpaRepository extends JpaRepository<Comment, Long>,
	JpaSpecificationExecutor<Comment>, CommentRepository {
	List<Comment> findByPostId(Long postId);
}