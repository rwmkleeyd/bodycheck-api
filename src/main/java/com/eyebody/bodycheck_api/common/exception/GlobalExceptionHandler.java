package com.eyebody.bodycheck_api.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiError> handleNotFound(EntityNotFoundException ex, HttpServletRequest req) {
		log.warn("Entity not found: {}", ex.getMessage());
		ApiError err = new ApiError("NOT_FOUND", ex.getMessage(), req.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiError> handleBadRequest(IllegalArgumentException ex, HttpServletRequest req) {
		log.warn("Bad request: {}", ex.getMessage());
		ApiError err = new ApiError("BAD_REQUEST", ex.getMessage(), req.getRequestURI());
		return ResponseEntity.badRequest().body(err);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleInternalError(Exception ex, HttpServletRequest req) {
		log.error("Unhandled exception", ex);
		ApiError err = new ApiError("INTERNAL_ERROR", "서버 오류가 발생했습니다.", req.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}
}
