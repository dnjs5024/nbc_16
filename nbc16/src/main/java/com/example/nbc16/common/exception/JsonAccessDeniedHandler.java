package com.example.nbc16.common.exception;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class JsonAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
		org.springframework.security.access.AccessDeniedException accessDeniedException) throws
		IOException,
		ServletException {

		response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String body = """
			{
			  "error": {
			    "code": "ACCESS_DENIED",
			    "message": "관리자 권한이 필요한 요청입니다. 접근 권한이 없습니다."
			  }
			}
			""";
		response.getWriter().write(body);
		response.getWriter().flush();
	}
}
