package com.example.nbc16.domain.auth.controller;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nbc16.common.dto.BaseResponse;
import com.example.nbc16.common.dto.ResponseCode;
import com.example.nbc16.domain.auth.dto.response.AuthResponseDto;
import com.example.nbc16.domain.auth.dto.request.LoginRequestDto;
import com.example.nbc16.domain.auth.dto.request.SignInRequestDto;
import com.example.nbc16.domain.auth.service.AuthService;



@RestController
@RequestMapping("/auths")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/signin")
	public ResponseEntity<BaseResponse<AuthResponseDto>> signIn(@RequestBody @Valid SignInRequestDto signInRequest) {
		AuthResponseDto authResponse = authService.signIn(signInRequest);
		return ResponseEntity.ok(BaseResponse.success(authResponse, ResponseCode.OK));
	}

	/*
	로그인
	 */
	@PostMapping("/login")
	public ResponseEntity<BaseResponse<String>> login(@RequestBody @Valid LoginRequestDto loginRequest) {
		String token = authService.logIn(loginRequest);
		return ResponseEntity.ok(BaseResponse.success(token, ResponseCode.OK));
	}


}
