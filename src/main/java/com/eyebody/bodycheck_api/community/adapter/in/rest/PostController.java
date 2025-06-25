package com.eyebody.bodycheck_api.community.adapter.in.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyebody.bodycheck_api.community.application.service.PostAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/v1/posts")
@RestController
public class PostController {

	private final PostAdapter postAdapter;

	@GetMapping("/")
	public String test() {
		return "";
	}
}
