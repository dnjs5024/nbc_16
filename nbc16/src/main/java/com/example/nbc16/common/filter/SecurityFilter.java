package com.example.nbc16.common.filter;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.nbc16.common.util.CustomUserDetails;
import com.example.nbc16.common.util.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
@Slf4j
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private static final List<String> SKIP_PATHS = List.of(
		"/auths"
);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		String url = request.getRequestURI();

		for (String skip : SKIP_PATHS) {
			if (url.startsWith(skip)) {
				filterChain.doFilter(request, response);
				return;
			}
		}

		String token = jwtUtil.subStringToken(request);

		try {
			if (token != null && jwtUtil.validationToken(token)) {

				Claims claim = jwtUtil.extractClaims(token);

				Long userId = Long.parseLong(claim.getSubject());
				String name = claim.get("name", String.class);
				String userRole = claim.get("userRole", String.class);

				List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + userRole));

				CustomUserDetails customUserDetails = new CustomUserDetails(userId, name, authorities);

				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
					customUserDetails,
					null,
					authorities
				);

				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (SecurityException | MalformedJwtException e) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "유효하지 않는 JWT 서명입니다.");
			return;
		} catch (ExpiredJwtException e) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "만료된 JWT 토큰입니다.");
			return;
		} catch (UnsupportedJwtException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "지원되지 않는 JWT 토큰입니다.");
			return;
		} catch (Exception e) {
			log.error("Internal server error", e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		filterChain.doFilter(request, response);
	}

}
