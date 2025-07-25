package com.eyebody.bodycheck_api.community.adapter.rest.dto.res;

import com.eyebody.bodycheck_api.community.domain.model.Board;

public record BoardResponse(Long id, String name, String description) {
	public static BoardResponse from(Board b) {
		return new BoardResponse(b.getId(), b.getName(), b.getDescription());
	}
}