package com.eyebody.bodycheck_api.community.domain.repository;

import java.util.List;
import java.util.Optional;

import com.eyebody.bodycheck_api.community.domain.model.Board;

public interface BoardRepository {
	List<Board> findAll();

	Optional<Board> findById(Long id);

	void save(Board board);

	void deleteById(Long id);

}
