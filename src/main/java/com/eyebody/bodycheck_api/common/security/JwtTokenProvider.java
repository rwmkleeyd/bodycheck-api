package com.eyebody.bodycheck_api.common.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	private static final long EXP = 1000 * 60 * 60;           // 1h
	private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public String generate(Long userId, String role) {
		Date now = new Date();
		Date exp = new Date(now.getTime() + 1000L * 60 * 60 * 24); // 24h, 필요한 값으로
		return Jwts.builder()
			.setSubject(String.valueOf(userId))   // ★ subject = userId
			.claim("role", role)                  // ★ role 클레임
			.setIssuedAt(now)
			.setExpiration(exp)
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();
	}

	public Jws<Claims> parse(String token) {
		return Jwts.parser()           // static builder
			.setSigningKey(key)        // 비밀키 / 공개키
			.build()                   // JwtParser
			.parseClaimsJws(token);    // 검증 + 파싱
	}
}