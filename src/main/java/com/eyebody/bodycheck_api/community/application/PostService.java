package com.eyebody.bodycheck_api.community.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eyebody.bodycheck_api.community.adapter.rest.dto.res.PostResponse;
import com.eyebody.bodycheck_api.community.application.in.PostUseCase;
import com.eyebody.bodycheck_api.community.domain.manager.PostManager;
import com.eyebody.bodycheck_api.community.domain.model.Post;
import com.eyebody.bodycheck_api.community.infra.jpa.JpaPostJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PostService implements PostUseCase {

	private final JpaPostJpaRepository jpaPostJpaRepository;
	private final PostManager postManager;

	@Transactional
	public PostResponse createPost(String title, String content, Long authorId) {
		assert title != null && !title.isBlank() : "Title must not be empty";
		Post post = jpaPostJpaRepository.save(new Post(title, content, authorId));
		return PostResponse.from(post);
	}

	@Transactional
	public PostResponse updatePost(Long id, String title, String content) {
		assert title != null && !title.isBlank() : "Title must not be empty";
		Post post = jpaPostJpaRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Post not found"));
		post.updatePost(title,  content);
		return PostResponse.from(post);
	}

	public PostResponse getPost(Long id) {
		assert id != null : "Post ID must not be null";
		Post post = jpaPostJpaRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Post not found"));
		return PostResponse.from(post);
	}

	public List<PostResponse> listPosts() {
		return jpaPostJpaRepository.findAll().stream()
			.map(PostResponse::from)
			.toList();
	}

	@Transactional
	public void deletePost(Long id) {
		assert id != null : "Post ID must not be null";
		jpaPostJpaRepository.deleteById(id);
	}
}
