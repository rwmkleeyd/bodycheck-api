package com.eyebody.bodycheck_api.community.domain.model;

import java.time.LocalDateTime;

import com.eyebody.bodycheck_api.community.adapter.rest.dto.res.CommentResponse;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long postId;

	@Column(nullable = false)
	private Long authorId;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;

	@Column(nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(nullable = false)
	private LocalDateTime updatedAt = LocalDateTime.now();

	@Builder
	public Comment(Long postId, Long authorId, String content) {
		this.postId = postId;
		this.authorId = authorId;
		this.content = content;
	}

	public CommentResponse from(Comment comment) {
		return new CommentResponse(
			comment.getId(),
			comment.getPostId(),
			comment.getAuthorId(),
			comment.getContent()
		);
	}

	public void updateContent(String content) {
		this.content = content;
		this.updatedAt = LocalDateTime.now();
	}
}
