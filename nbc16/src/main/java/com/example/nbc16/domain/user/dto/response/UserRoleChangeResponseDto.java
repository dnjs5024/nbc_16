package com.example.nbc16.domain.user.dto.response;

import java.util.List;

import lombok.Getter;

import com.example.nbc16.domain.user.entity.UserRole;

@Getter
public class UserRoleChangeResponseDto {

	private final String userName;

	private final String nickName;

	private final List<UserRole> roles;

	public UserRoleChangeResponseDto(String userName, String nickName, List<UserRole> roles) {
		this.userName = userName;
		this.nickName = nickName;
		this.roles = roles;
	}

	public static UserRoleChangeResponseDto from(String userName, String nickName, List<UserRole> roles){
		return new UserRoleChangeResponseDto(userName, nickName, roles);
	}

}
