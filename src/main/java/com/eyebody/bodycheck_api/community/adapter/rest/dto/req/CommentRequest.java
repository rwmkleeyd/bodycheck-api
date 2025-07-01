package com.eyebody.bodycheck_api.community.adapter.rest.dto.req;

public record CommentRequest(
	Long postId,
	Long authorId,
	String content
) {}