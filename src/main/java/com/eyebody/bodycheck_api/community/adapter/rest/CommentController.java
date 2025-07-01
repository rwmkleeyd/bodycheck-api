package com.eyebody.bodycheck_api.community.adapter.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyebody.bodycheck_api.community.adapter.rest.dto.req.CommentRequest;
import com.eyebody.bodycheck_api.community.adapter.rest.dto.res.CommentResponse;
import com.eyebody.bodycheck_api.community.application.usecase.CommentUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/comments")
public class CommentController {

	private final CommentUseCase commentUseCase;

	@PostMapping
	public ResponseEntity<CommentResponse> create(@RequestBody @Valid CommentRequest req) {
		return ResponseEntity.ok(commentUseCase.createComment(req.postId(), req.authorId(), req.content()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CommentResponse> get(@PathVariable Long id) {
		return ResponseEntity.ok(commentUseCase.getComment(id));
	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<List<CommentResponse>> list(@PathVariable Long postId) {
		List<CommentResponse> list = commentUseCase.listComments(postId);
		return ResponseEntity.ok(list);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CommentResponse> update(
		@PathVariable Long id,
		@RequestBody @Valid CommentRequest req
	) {
		CommentResponse c = commentUseCase.updateComment(id, req.content());
		return ResponseEntity.ok(toResponse(c));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		commentUseCase.deleteComment(id);
		return ResponseEntity.noContent().build();
	}
}
