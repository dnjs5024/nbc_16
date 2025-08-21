package com.example.nbc16.domain.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nbc16.common.dto.BaseResponse;
import com.example.nbc16.common.dto.ResponseCode;
import com.example.nbc16.common.exception.BusinessException;
import com.example.nbc16.domain.auth.dto.response.AuthResponseDto;
import com.example.nbc16.domain.auth.dto.request.LoginRequestDto;
import com.example.nbc16.domain.auth.dto.request.SignInRequestDto;
import com.example.nbc16.domain.auth.service.AuthService;



@RestController
@RequestMapping("/auths")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	@Operation(
		summary = "회원 가입",
		description = "회원가입 유저,관리자로 가입 가능",
		tags = {"Auth API"}
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공",
			content = @Content(mediaType = "application/json",
				schema = @Schema(implementation = AuthResponseDto.class))),
		@ApiResponse(responseCode = "409", description = "이미 가입된 사용자입니다.",
			content = @Content(schema = @Schema(defaultValue = "{\n"
				+ "  \"error\": {\n"
				+ "    \"code\": \"USER_ALREADY_EXISTS\",\n"
				+ "    \"message\": \"이미 가입된 사용자입니다.\"\n"
				+ "  }\n"
				+ "}")))
	})
	@PostMapping("/signin")
	public ResponseEntity<BaseResponse<AuthResponseDto>> signIn(@RequestBody @Valid SignInRequestDto signInRequest) {
		AuthResponseDto authResponse = authService.signIn(signInRequest);
		return ResponseEntity.ok(BaseResponse.success(authResponse, ResponseCode.OK));
	}

	@Operation(
		summary = "로그인",
		description = "가입한 아이디로 로그인 아이디,비번 틀리면 에러",
		tags = {"Auth API"}
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공",
			content = @Content(mediaType = "application/json",
				schema = @Schema(defaultValue = "token"))),
		@ApiResponse(responseCode = "401", description = "아이디 또는 비밀번호가 올바르지 않습니다.",
			content = @Content(schema = @Schema(defaultValue = "{\n"
				+ "  \"error\": {\n"
				+ "    \"code\": \"INVALID_CREDENTIALS\",\n"
				+ "    \"message\": \"아이디 또는 비밀번호가 올바르지 않습니다.\"\n"
				+ "  }\n"
				+ "}")))
	})
	@PostMapping("/login")
	public ResponseEntity<BaseResponse<String>> login(@RequestBody @Valid LoginRequestDto loginRequest) {
		String token = authService.logIn(loginRequest);
		return ResponseEntity.ok(BaseResponse.success(token, ResponseCode.OK));
	}


}
