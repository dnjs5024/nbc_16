package com.example.nbc16.domain.auth.dto.response;

import java.util.List;

import lombok.Getter;

import com.example.nbc16.domain.user.entity.User;
import com.example.nbc16.domain.user.entity.UserRole;

@Getter
public class AuthResponseDto {

	private final String userName;

	private final String nickName;

	private final List<UserRole> roles;

	public AuthResponseDto(String userName, String nickName, List<UserRole> roles) {
		this.userName = userName;
		this.nickName = nickName;
		this.roles = roles;
	}

	public static AuthResponseDto from(String userName, String nickName, List<UserRole> roles){
		return new AuthResponseDto(userName, nickName, roles);

	}
}
