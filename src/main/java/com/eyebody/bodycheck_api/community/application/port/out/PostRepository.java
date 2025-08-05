package com.eyebody.bodycheck_api.community.application.port.out;

import java.util.Collection;
import java.util.Optional;

import com.eyebody.bodycheck_api.community.domain.model.Post;

public interface PostRepository {

	Post save(Post post);

	Optional<Post> findById(Long id);

	void deleteById(Long id);

	Collection<Post> findAll();
}
