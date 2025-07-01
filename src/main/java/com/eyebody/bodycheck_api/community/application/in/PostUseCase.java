package com.eyebody.bodycheck_api.community.application.in;

import java.util.List;

import com.eyebody.bodycheck_api.community.adapter.rest.dto.res.PostResponse;

public interface PostUseCase {
	PostResponse createPost(String title, String content, Long authorId);

	PostResponse updatePost(Long id, String title, String content);

	PostResponse getPost(Long id);

	List<PostResponse> listPosts();

	void deletePost(Long id);
}
