package com.eyebody.bodycheck_api.community.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eyebody.bodycheck_api.community.adapter.in.rest.dto.res.PostResponse;
import com.eyebody.bodycheck_api.community.application.port.PostUseCase;
import com.eyebody.bodycheck_api.community.domain.port.PostRepository;
import com.eyebody.bodycheck_api.community.domain.manager.PostManager;
import com.eyebody.bodycheck_api.community.domain.model.Post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PostService implements PostUseCase {

	private final PostRepository postRepository;
	private final PostManager postManager;

	@Transactional
	public PostResponse createPost(String title, String content, Long authorId) {
		assert title != null && !title.isBlank() : "Title must not be empty";
		Post post = postRepository.save(new Post(title, content, authorId));
		return PostResponse.from(post);
	}

	@Transactional
	public PostResponse updatePost(Long id, String title, String content) {
		assert title != null && !title.isBlank() : "Title must not be empty";
		Post post = postRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Post not found"));
		post.updatePost(title, content);
		return PostResponse.from(post);
	}

	public PostResponse getPost(Long id) {
		assert id != null : "Post ID must not be null";
		Post post = postRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Post not found"));
		return PostResponse.from(post);
	}

	public List<PostResponse> listPosts() {
		return postRepository.findAll().stream()
			.map(PostResponse::from)
			.toList();
	}

	@Transactional
	public void deletePost(Long id) {
		assert id != null : "Post ID must not be null";
		postRepository.deleteById(id);
	}
}
