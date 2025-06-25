package com.eyebody.bodycheck_api.common.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

	private final JwtTokenProvider provider;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
		throws ServletException, IOException {

		String bearer = req.getHeader("Authorization");
		if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
			try {
				var claims = provider.parse(bearer.substring(7)).getBody();
				Long id = Long.valueOf(claims.getSubject());
				String role = claims.get("role", String.class);
				var auth = new UsernamePasswordAuthenticationToken(
					id, null, List.of(new SimpleGrantedAuthority(role)));
				SecurityContextHolder.getContext().setAuthentication(auth);
			} catch (Exception ignored) { }  // 토큰 오류 → 익명 처리
		}
		chain.doFilter(req, res);
	}
}