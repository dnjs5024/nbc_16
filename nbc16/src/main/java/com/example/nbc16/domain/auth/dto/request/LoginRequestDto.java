package com.example.nbc16.domain.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class LoginRequestDto {

	@Schema(description = "사용자 ID", example = "홍길동")
	private String userName;
	@Schema(description = "사용자 PWD", example = "12345678")
	private String password;
}
