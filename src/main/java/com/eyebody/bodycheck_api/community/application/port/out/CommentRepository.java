package com.eyebody.bodycheck_api.community.application.port.out;

import java.util.List;
import java.util.Optional;

import com.eyebody.bodycheck_api.community.domain.model.Comment;

public interface CommentRepository {

	Comment save(Comment comment);

	Optional<Comment> findById(Long id);

	List<Comment> findByPostId(Long postId);

	void deleteById(Long id);
}
