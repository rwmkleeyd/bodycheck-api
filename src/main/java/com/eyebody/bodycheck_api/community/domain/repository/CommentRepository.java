package com.eyebody.bodycheck_api.community.domain.repository;

import java.util.List;
import java.util.Optional;

import com.eyebody.bodycheck_api.community.domain.model.Comment;

public interface CommentRepository {
	List<Comment> findAll();

	Optional<Comment> findById(Long id);

	void save(Comment comment);

	void deleteById(Long id);
}
