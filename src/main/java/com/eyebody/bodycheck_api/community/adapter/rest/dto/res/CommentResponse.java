package com.eyebody.bodycheck_api.community.adapter.rest.dto.res;

import com.eyebody.bodycheck_api.community.domain.model.Comment;

public record CommentResponse(
	Long id,
	Long postId,
	Long authorId,
	String content
) {
	public static CommentResponse from(Comment comment) {
		return new CommentResponse(
			comment.getId(),
			comment.getPostId(),
			comment.getAuthorId(),
			comment.getContent()
		);
	}
}