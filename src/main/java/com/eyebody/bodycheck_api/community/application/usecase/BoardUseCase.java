package com.eyebody.bodycheck_api.community.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eyebody.bodycheck_api.community.domain.service.BoardPojoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BoardUseCase {

	private final BoardPojoService boardService = new BoardPojoService();

}
