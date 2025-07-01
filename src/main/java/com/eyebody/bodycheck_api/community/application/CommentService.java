package com.eyebody.bodycheck_api.community.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eyebody.bodycheck_api.community.adapter.rest.dto.res.CommentResponse;
import com.eyebody.bodycheck_api.community.application.in.CommentUseCase;
import com.eyebody.bodycheck_api.community.domain.manager.CommentManager;
import com.eyebody.bodycheck_api.community.domain.model.Comment;
import com.eyebody.bodycheck_api.community.infra.jpa.JpaCommentJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CommentService implements CommentUseCase {

	private final JpaCommentJpaRepository jpaCommentJpaRepository;
	private final CommentManager commentManager;

	@Transactional
	public CommentResponse createComment(Long postId, Long authorId, String content) {
		assert postId != null && authorId != null && content != null && !content.isBlank();
		Comment comment = jpaCommentJpaRepository.save(new Comment(postId, authorId, content));
		return comment.from(comment);
	}

	public CommentResponse getComment(Long id) {
		Comment comment = jpaCommentJpaRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Comment not found"));
		return comment.from(comment);
	}

	// TODO: Consider using a DTO instead of Object
	public List<CommentResponse> listComments(Long postId) {
		assert postId != null : "Post ID must not be null";
		List<Comment> comments = jpaCommentJpaRepository.findByPostId(postId);
		return comments.stream()
			.map(CommentResponse::from)
			.collect(Collectors.toList());
	}

	@Transactional
	public CommentResponse updateComment(Long id, String content) {
		Comment c = jpaCommentJpaRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Comment not found"));
		c.setContent(content);
		c.setUpdatedAt(java.time.LocalDateTime.now());
		return jpaCommentJpaRepository.save(c);
	}

	@Transactional
	public void deleteComment(Long id) {
		jpaCommentJpaRepository.deleteById(id);
	}
}
