package com.eyebody.bodycheck_api.community.adapter.in.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyebody.bodycheck_api.community.application.service.CommentAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/comments")
public class CommentController {

	private final CommentAdapter commentAdapter;

	@GetMapping("/")
	public String test() {
		return "";
	}
}
