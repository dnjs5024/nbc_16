package com.example.nbc16.domain.auth.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.example.nbc16.domain.user.entity.UserRole;

@Getter
public class SignInRequestDto {
	@Schema(description = "사용자 ID", example = "홍길동")
	private String userName;
	@Schema(description = "사용자 PWD", example = "12345678")
	private String password;
	@Schema(description = "사용자 별명", example = "스스프프링링")
	private String nickName;
	@Schema(description = "사용자 권한", example = "ADMIN")
	private UserRole userRole;

}
