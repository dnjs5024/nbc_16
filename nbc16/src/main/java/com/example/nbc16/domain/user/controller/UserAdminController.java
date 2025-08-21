package com.example.nbc16.domain.user.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nbc16.common.dto.BaseResponse;
import com.example.nbc16.common.dto.ResponseCode;
import com.example.nbc16.domain.user.dto.response.UserRoleChangeResponseDto;
import com.example.nbc16.domain.user.service.UserAdminService;
import com.fasterxml.jackson.databind.ser.Serializers;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class UserAdminController {

	private final UserAdminService userAdminService;

	@PatchMapping("/users/{userId}")
	public ResponseEntity<BaseResponse<UserRoleChangeResponseDto>>  changeUserRole(@PathVariable long userId) {
		return ResponseEntity.ok(BaseResponse.success(userAdminService.changeUserRole(userId), ResponseCode.OK));
	}
}
