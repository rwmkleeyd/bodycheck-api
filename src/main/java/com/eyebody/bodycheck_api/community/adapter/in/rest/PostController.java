package com.eyebody.bodycheck_api.community.adapter.in.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyebody.bodycheck_api.community.application.usecase.PostUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/v1/posts")
@RestController
public class PostController {

	private final PostUseCase postUseCase;

	@GetMapping("/")
	public String test() {
		return "";
	}
}
