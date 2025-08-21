package com.example.nbc16.common.config;

import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.nbc16.common.filter.SecurityFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

	private final SecurityFilter securityFilter;
	// todo : 예외 핸들러

	private static final String[] PUBLIC_URIS = {
		"/auths/**",
		"/login/**",
		"/h2-console",
		"/h2-console/*",
		"/swagger",
		"/swagger-ui.html",
		"/swagger-ui/**",
		"/api-docs/**"
	};


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
			.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
			.headers(h -> h.frameOptions(f -> f.sameOrigin()))
			.httpBasic(AbstractHttpConfigurer::disable)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(
				auth -> auth
					.requestMatchers(PUBLIC_URIS)
					.permitAll()
					.requestMatchers("/admin/**").hasRole("ADMIN")
					.anyRequest()
					.authenticated()
			)
			.formLogin(formLogin -> formLogin.disable())
			.logout(logout->logout.logoutUrl("/logout")
				.logoutSuccessHandler((request, response, authentication) -> response
					.setStatus(HttpServletResponse.SC_OK)
				)
			)
			.addFilterBefore(securityFilter,
				UsernamePasswordAuthenticationFilter.class); // 기존 필터보다 내가 커스텀한 필터를 앞에 놓기 위해 사용

		return http.build();
	}

}
