package com.eyebody.bodycheck_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.eyebody.bodycheck_api.common.security.JwtAuthFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final JwtAuthFilter jwtFilter;
	public SecurityConfig(JwtAuthFilter jwtFilter){ this.jwtFilter = jwtFilter; }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
			.cors(cors -> cors.configurationSource(corsConfigurationSource()))
			.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(req -> req
				.requestMatchers("/**").permitAll()
				.anyRequest().authenticated());

		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	// 프런트(Nuxt) 도메인에 대해 allowedOrigins 설정을 위해 추가 (@CrossOrigin("http://localhost:3000") 등으로 각 컨트롤러에 설정해도 됨)
	@Bean
	public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
		var config = new org.springframework.web.cors.CorsConfiguration();
		config.setAllowedOrigins(java.util.List.of(
			"http://localhost:3000",          // Nuxt dev
			"https://your-nuxt-domain.com"    // 프로덕션
		));
		config.setAllowedMethods(java.util.List.of("GET","POST","PUT","DELETE","OPTIONS"));
		config.setAllowedHeaders(java.util.List.of("*"));
		config.setAllowCredentials(true);     // JWT 쿠키/헤더 전달 허용

		var source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

	@Bean public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
}