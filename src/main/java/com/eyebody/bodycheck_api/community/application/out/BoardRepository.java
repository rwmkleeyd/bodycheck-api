package com.eyebody.bodycheck_api.community.application.out;

import java.util.List;
import java.util.Optional;

import com.eyebody.bodycheck_api.community.domain.model.Board;

// 구현체는 누군지 모르겠고 사용법만 정의해놓음
public interface BoardRepository {

	Optional<Board> findById(Long id);

	List<Board> findAll();

	Board save(Board board);

	void deleteById(Long id);

}
