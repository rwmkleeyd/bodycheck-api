package com.eyebody.bodycheck_api.community.adapter.in.rest.dto.res;

import com.eyebody.bodycheck_api.community.domain.model.Post;

public record PostResponse(
	Long id,
	String title,
	String content,
	Long authorId
) {
	public static PostResponse from(Post post) {
		return new PostResponse(post.getId(), post.getTitle(), post.getContent(), post.getAuthorId());
	}
}