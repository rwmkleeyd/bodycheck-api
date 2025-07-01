package com.eyebody.bodycheck_api.community.application.in;

import java.util.List;

import com.eyebody.bodycheck_api.community.adapter.rest.dto.req.BoardRequest;
import com.eyebody.bodycheck_api.community.adapter.rest.dto.res.BoardResponse;

public interface BoardUseCase {
	BoardResponse create(BoardRequest req);

	List<BoardResponse> list();

	BoardResponse get(Long id);

	void delete(Long id);
}
