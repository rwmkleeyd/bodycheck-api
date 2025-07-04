package com.eyebody.bodycheck_api.common.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

	/** JwtAuthFilter 가 심어둔 principal(Long) 반환 */
	public static Long currentUserId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || auth.getPrincipal() == null)
			throw new IllegalStateException("Unauthenticated");
		return (Long) auth.getPrincipal();   // ← Long으로 바로 캐스팅
	}
}