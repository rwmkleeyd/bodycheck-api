package com.eyebody.bodycheck_api.community.adapter.in.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyebody.bodycheck_api.community.adapter.in.rest.dto.req.BoardRequest;
import com.eyebody.bodycheck_api.community.adapter.in.rest.dto.res.BoardResponse;
import com.eyebody.bodycheck_api.community.application.port.BoardUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
// FixMe: 개인화 작업은 나중에 auth 쪽 작업 완료되면 한번에 진행하기
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/boards")
public class BoardController {

	private final BoardUseCase useCase;

	@PostMapping
	public ResponseEntity<BoardResponse> create(@RequestBody @Valid BoardRequest req) {
		return ResponseEntity.ok(useCase.create(req));
	}

	@GetMapping
	public List<BoardResponse> list() {
		return useCase.list();
	}

	@GetMapping("/{id}")
	public BoardResponse get(@PathVariable Long id) {
		return useCase.get(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		useCase.delete(id);
		return ResponseEntity.noContent().build();
	}
}
