package com.eyebody.bodycheck_api.community.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eyebody.bodycheck_api.community.domain.service.BoardPojoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BoardAdapter {

	private final BoardPojoService boardService = new BoardPojoService();

}
