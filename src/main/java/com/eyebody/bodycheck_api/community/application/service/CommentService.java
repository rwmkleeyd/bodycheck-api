package com.eyebody.bodycheck_api.community.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eyebody.bodycheck_api.community.adapter.in.rest.dto.res.CommentResponse;
import com.eyebody.bodycheck_api.community.application.port.in.CommentUseCase;
import com.eyebody.bodycheck_api.community.application.port.out.CommentRepository;
import com.eyebody.bodycheck_api.community.domain.manager.CommentManager;
import com.eyebody.bodycheck_api.community.domain.model.Comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CommentService implements CommentUseCase {

	private final CommentRepository commentRepository;
	private final CommentManager commentManager;

	@Transactional
	public CommentResponse createComment(Long postId, Long authorId, String content) {
		assert postId != null && authorId != null && content != null && !content.isBlank();
		Comment comment = commentRepository.save(new Comment(postId, authorId, content));
		return comment.from(comment);
	}

	public CommentResponse getComment(Long id) {
		Comment comment = commentRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Comment not found"));
		return comment.from(comment);
	}

	public List<CommentResponse> listComments(Long postId) {
		assert postId != null : "Post ID must not be null";
		List<Comment> comments = commentRepository.findByPostId(postId);
		if (comments.isEmpty()) {
			log.warn("No comments found for post ID: {}", postId);
			return List.of();
		}
		return comments.stream()
			.map(CommentResponse::from)
			.collect(Collectors.toList());
	}

	@Transactional
	public void updateComment(Long id, String content) {
		Comment comment = commentRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Comment not found"));
		comment.updateContent(content);
	}

	@Transactional
	public void deleteComment(Long id) {
		commentRepository.deleteById(id);
	}
}
