package com.example.nbc16.domain.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class LoginRequestDto {

	private String userName;

	private String password;
}
