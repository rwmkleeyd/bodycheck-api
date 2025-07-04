package com.eyebody.bodycheck_api.community.domain.model;

public enum PostRole {
	USER("사용자"),
	ADMIN("관리자"),
	MODERATOR("운영자");

	private final String description;

	PostRole(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
