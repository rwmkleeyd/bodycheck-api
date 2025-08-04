package com.eyebody.bodycheck_api.community.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.eyebody.bodycheck_api.community.adapter.in.rest.dto.req.BoardRequest;
import com.eyebody.bodycheck_api.community.adapter.in.rest.dto.res.BoardResponse;
import com.eyebody.bodycheck_api.community.application.port.BoardUseCase;
import com.eyebody.bodycheck_api.community.domain.port.BoardRepository;
import com.eyebody.bodycheck_api.community.domain.manager.BoardManager;
import com.eyebody.bodycheck_api.community.domain.model.Board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BoardService implements BoardUseCase {

	// TODO: 포트만 알고 있으면 됨 (구현체는 뭘 쓰던 서비스는 노상관)
	private final BoardRepository boardRepository;
	private final BoardManager boardManager;

	@Transactional
	public BoardResponse create(BoardRequest req) {
		assert req.name() != null && req.description() != null;
		Board board = new Board(null, req.name(), req.description());
		Board saved = boardRepository.save(board);
		return BoardResponse.from(saved);
	}

	public List<BoardResponse> list() {
		return boardRepository.findAll().stream()
			.map(BoardResponse::from)
			.collect(Collectors.toList());
	}

	public BoardResponse get(Long id) {
		assert id != null;
		Board board = boardRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found"));
		return BoardResponse.from(board);
	}

	@Transactional
	public void delete(Long id) {
		assert id != null;
		boardRepository.deleteById(id);
	}

}

