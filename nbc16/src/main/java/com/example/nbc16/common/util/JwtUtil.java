package com.example.nbc16.common.util;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import com.example.nbc16.domain.user.entity.UserRole;

@Component
public class JwtUtil {

	private static final String BEARER_PREFIX = "Bearer ";
	private static final long TOKEN_TIME = 60 * 60 * 1000L;

	@Value("${jwt.secret}")
	private String secretKey;
	private Key key;
	private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	@PostConstruct
	public  void init() {
		byte[] bytes = Base64.getDecoder().decode(secretKey);
		key = Keys.hmacShaKeyFor(bytes);
	}

	public String createToken (Long userId, String email, UserRole userRole) {
		Date date = new Date();

		return BEARER_PREFIX + Jwts.builder()
			.setSubject(String.valueOf(userId))
			.claim("email", email)
			.claim("userRole", userRole)
			.setExpiration(new Date(date.getTime() + TOKEN_TIME))
			.setIssuedAt(date)
			.signWith(key, signatureAlgorithm)
			.compact();
	}

	public String subStringToken(HttpServletRequest request) {
		String tokenValue = request.getHeader("Authorization");

		if (StringUtils.hasText(tokenValue) && tokenValue.startsWith(BEARER_PREFIX)){
			return tokenValue.substring(BEARER_PREFIX.length());
		}

		return null;
	}

	public Claims extractClaims (String token) {
		return Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	public boolean validationToken (String token){
		try {
			extractClaims(token);
			return true;
		}
		catch (Exception e){
			return false;
		}
	}

	public Long expiration(String token) {
		Claims claims = Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody();

		return claims.getExpiration().getTime() - System.currentTimeMillis();
	}

}
