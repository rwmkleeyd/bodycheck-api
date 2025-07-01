package com.eyebody.bodycheck_api.community.domain.repository;

import java.util.List;
import java.util.Optional;

import com.eyebody.bodycheck_api.community.domain.model.Post;

public interface PostRepository {
	List<Post> findAll();

	Optional<Post> findById(Long id);

	Post save(Post post);

	void deleteById(Long id);
}
