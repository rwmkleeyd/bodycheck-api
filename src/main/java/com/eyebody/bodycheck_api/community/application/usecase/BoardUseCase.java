package com.eyebody.bodycheck_api.community.application.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.eyebody.bodycheck_api.community.adapter.in.rest.dto.request.BoardRequest;
import com.eyebody.bodycheck_api.community.adapter.in.rest.dto.response.BoardResponse;
import com.eyebody.bodycheck_api.community.domain.model.Board;
import com.eyebody.bodycheck_api.community.domain.service.BoardPojoService;
import com.eyebody.bodycheck_api.community.infra.persistence.BoardJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BoardUseCase {

	private final BoardJpaRepository boardJpaRepository;

	@Transactional
	public BoardResponse create(BoardRequest req) {
		assert req.name() != null && req.description() != null;
		Board board = new Board(null, req.name(), req.description());
		Board saved = boardJpaRepository.save(board);
		return BoardResponse.from(saved);
	}

	public List<BoardResponse> list() {
		return boardJpaRepository.findAll().stream()
			.map(BoardResponse::from)
			.collect(Collectors.toList());
	}

	public BoardResponse get(Long id) {
		assert id != null;
		Board board = boardJpaRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found"));
		return BoardResponse.from(board);
	}

	@Transactional
	public void delete(Long id) {
		assert id != null;
		boardJpaRepository.deleteById(id);
	}

}
