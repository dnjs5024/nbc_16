package com.example.nbc16.domain.auth.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.example.nbc16.domain.user.entity.UserRole;

@Getter
public class SignInRequestDto {

	private String userName;

	private String password;

	private String nickName;

	private UserRole userRole;

}
