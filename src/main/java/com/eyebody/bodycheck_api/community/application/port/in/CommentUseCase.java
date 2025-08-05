package com.eyebody.bodycheck_api.community.application.port.in;

import java.util.List;

import com.eyebody.bodycheck_api.community.adapter.in.rest.dto.res.CommentResponse;

public interface CommentUseCase {
	CommentResponse createComment(Long postId, Long authorId, String content);

	CommentResponse getComment(Long id);

	List<CommentResponse> listComments(Long postId);

	void updateComment(Long id, String content);

	void deleteComment(Long id);
}
