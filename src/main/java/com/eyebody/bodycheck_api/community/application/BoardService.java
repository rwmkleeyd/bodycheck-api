package com.eyebody.bodycheck_api.community.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.eyebody.bodycheck_api.community.adapter.rest.dto.req.BoardRequest;
import com.eyebody.bodycheck_api.community.adapter.rest.dto.res.BoardResponse;
import com.eyebody.bodycheck_api.community.application.in.BoardUseCase;
import com.eyebody.bodycheck_api.community.domain.manager.BoardManager;
import com.eyebody.bodycheck_api.community.domain.model.Board;
import com.eyebody.bodycheck_api.community.infra.jpa.JpaBoardJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BoardService implements BoardUseCase {

	private final JpaBoardJpaRepository jpaBoardJpaRepository;
	private final BoardManager boardManager;

	@Transactional
	public BoardResponse create(BoardRequest req) {
		assert req.name() != null && req.description() != null;
		Board board = new Board(null, req.name(), req.description());
		Board saved = jpaBoardJpaRepository.save(board);
		return BoardResponse.from(saved);
	}

	public List<BoardResponse> list() {
		return jpaBoardJpaRepository.findAll().stream()
			.map(BoardResponse::from)
			.collect(Collectors.toList());
	}

	public BoardResponse get(Long id) {
		assert id != null;
		Board board = jpaBoardJpaRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found"));
		return BoardResponse.from(board);
	}

	@Transactional
	public void delete(Long id) {
		assert id != null;
		jpaBoardJpaRepository.deleteById(id);
	}

}

