package com.example.nbc16.common.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import lombok.Getter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/*
principal을 기준으로 security는 인증, 인가, 감시, 토큰 생성 과정을 진행하기 때문에 여기에 필요한 값을 담을 필요가 있음
UserDetails는 폼 로그인/DAO 인증 체인에서 요구하는 principal 타입
jwt 토큰을 만들기 위해 사용자 내용을 담은 객제 -> dto
 */
@Getter
public class CustomUserDetails implements UserDetails{

	private final Long userId;
	private final String name;
	private final List<SimpleGrantedAuthority> authorities;
	private Map<String, Object> attributes;

	public CustomUserDetails(Long userId, String name, List<SimpleGrantedAuthority> authorities)
	{
		this.userId = userId;
		this.name = name;
		this.authorities = authorities;
	}

	public CustomUserDetails(Long userId, String name, List<SimpleGrantedAuthority> authorities, Map<String, Object> attributes)
	{
		this.userId = userId;
		this.name = name;
		this.authorities = authorities;
		this.attributes = attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return "";
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return UserDetails.super.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return UserDetails.super.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return UserDetails.super.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return UserDetails.super.isEnabled();
	}


}
