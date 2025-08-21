package com.example.nbc16.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nbc16.common.dto.BaseResponse;
import com.example.nbc16.common.dto.ResponseCode;
import com.example.nbc16.common.exception.BusinessException;
import com.example.nbc16.domain.user.dto.response.UserRoleChangeResponseDto;
import com.example.nbc16.domain.user.service.UserAdminService;
import com.fasterxml.jackson.databind.ser.Serializers;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class UserAdminController {

	private final UserAdminService userAdminService;

	@Operation(
		summary = "관리자 권한 부여",
		description = "관리자 권한이 있는 아이디로 일반 유저에게 관리자 권한 부여 가능 관리자 권한만 접근 가능",
		tags = {"User API"}
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "성공",
			content = @Content(mediaType = "application/json",
				schema = @Schema(implementation = UserRoleChangeResponseDto.class))),
		@ApiResponse(responseCode = "404", description = "존재하지 않는 사용자 입니다.",
			content = @Content(schema = @Schema(defaultValue = "{\n"
				+ "  \"error\": {\n"
				+ "    \"code\": \"USER_NOT_FOUND\",\n"
				+ "    \"message\": \"존재하지 않는 사용자 입니다.\"\n"
				+ "  }\n"
				+ "}"))),
		@ApiResponse(responseCode = "403", description = "존재하지 않는 사용자 입니다.",
			content = @Content(schema = @Schema(defaultValue = "{\n"
				+ "  \"error\": {\n"
				+ "    \"code\": \"ACCESS_DENIED\",\n"
				+ "    \"message\": \"관리자 권한이 필요한 요청입니다. 접근 권한이 없습니다.\"\n"
				+ "  }\n"
				+ "}")))
	})
	@PatchMapping("/users/{userId}")
	public ResponseEntity<BaseResponse<UserRoleChangeResponseDto>>  changeUserRole(@PathVariable @Schema(description = "권한 부여할 유저 DB 식별자", example = "2") long userId) {
		return ResponseEntity.ok(BaseResponse.success(userAdminService.changeUserRole(userId), ResponseCode.OK));
	}
}
