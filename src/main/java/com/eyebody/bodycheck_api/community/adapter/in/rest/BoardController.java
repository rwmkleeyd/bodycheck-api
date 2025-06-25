package com.eyebody.bodycheck_api.community.adapter.in.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyebody.bodycheck_api.community.application.usecase.BoardUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/boards")
public class BoardController {

	private final BoardUseCase boardUseCase;

	@GetMapping("/")
	public String test() {
		return "";
	}
}
