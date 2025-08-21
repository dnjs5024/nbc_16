package com.example.nbc16.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import org.springframework.http.HttpStatus;

@Getter
@ToString
@AllArgsConstructor
public enum ResponseCode {

	/* JSON 결과 */
	OK(HttpStatus.OK, "요청 처리 성공"),
	CREATED(HttpStatus.CREATED, "요청 처리 성공"),
	NO_CONTENT(HttpStatus.NO_CONTENT, "요청 처리 성공"),
	FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "요청 처리 실패"),
	DB_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "요청 DB 처리 실패"),
	VALID_FAIL(HttpStatus.BAD_REQUEST, "유효성 검증에 실패하였습니다."),
	NOT_FOUND(HttpStatus.NOT_FOUND, "NOT FOUND"),

	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 사용자 입니다."),
	USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 가입된 사용자입니다."),
	INVALID_CREDENTIALS(HttpStatus.BAD_REQUEST, "아이디 또는 비밀번호가 올바르지 않습니다."),


;

	private final HttpStatus status;
	private final String defaultMessage;
}
