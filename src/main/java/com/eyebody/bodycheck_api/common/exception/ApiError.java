package com.eyebody.bodycheck_api.common.exception;

public record ApiError(String code, String message, String path) {}