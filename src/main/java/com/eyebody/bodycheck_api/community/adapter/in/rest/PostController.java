package com.eyebody.bodycheck_api.community.adapter.in.rest;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.eyebody.bodycheck_api.community.adapter.in.rest.dto.request.PostRequest;
import com.eyebody.bodycheck_api.community.adapter.in.rest.dto.response.PostResponse;
import com.eyebody.bodycheck_api.community.application.usecase.PostUseCase;
import com.eyebody.bodycheck_api.community.domain.model.Post;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/v1/posts")
@RestController
public class PostController {

	private final PostUseCase postUseCase;

	@PostMapping
	public ResponseEntity<PostResponse> create(@RequestBody @Valid PostRequest req) {
		PostResponse created = postUseCase.createPost(req.title(), req.content(), req.authorId());
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest().path("/{id}")
			.buildAndExpand(created.id()).toUri();
		return ResponseEntity.created(location).body(created);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostResponse> get(@PathVariable Long id) {
		return ResponseEntity.ok(postUseCase.getPost(id));
	}

	@GetMapping
	public ResponseEntity<List<PostResponse>> list() {
		List<PostResponse> list = postUseCase.listPosts();
		return ResponseEntity.ok(list);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostResponse> update(
		@PathVariable Long id,
		@RequestBody @Valid PostRequest req) {
		PostResponse updated = postUseCase.updatePost(id, req.title(), req.content());
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		postUseCase.deletePost(id);
		return ResponseEntity.noContent().build();
	}
}