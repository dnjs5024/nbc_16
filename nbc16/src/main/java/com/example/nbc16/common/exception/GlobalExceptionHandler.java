package com.example.nbc16.common.exception;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.nbc16.common.dto.BaseResponse;
import com.example.nbc16.common.dto.ResponseCode;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * BusinessException 처리 핸들러.
     * BusinessException 발생 시, 해당 예외의 ResultCode에 맞춰 응답을 생성.
     *
     * @param request   HTTP 요청 정보
     * @param ex        발생한 BusinessException
     * @return          ResponseEntity에 담긴 BaseResponse 객체
     */
    @ExceptionHandler(BusinessException.class)
    public Object handleBusinessException(HttpServletRequest request, BusinessException ex) {

        return ResponseEntity
            .status(ex.getResponseCode().getStatus())
            .body(BaseResponse.error(ex.getResponseCode(), ex));
    }


    /**
     * MethodArgumentNotValidException 처리 핸들러.
     * 유효성 검증 실패 시, BAD_REQUEST(400) 상태와 함께 응답을 반환.
     *
     * @param request   HTTP 요청 정보
     * @param e         발생한 MethodArgumentNotValidException
     * @return          ResponseEntity에 담긴 BaseResponse 객체
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleValidationException(HttpServletRequest request, MethodArgumentNotValidException e) {

        // 유효성 검증 실패한 필드 정보 추출
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        // 에러 메시지 추출, 필드 에러가 없으면 기본 메시지 사용
        String message = fieldErrors.isEmpty() ? ResponseCode.VALID_FAIL.getDefaultMessage() :
            fieldErrors.get(fieldErrors.size() - 1).getDefaultMessage();

        // BAD_REQUEST 상태로 응답 생성
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(BaseResponse.error(ResponseCode.VALID_FAIL, message));
    }

    /**
     * 기타 모든 예외 처리 핸들러.
     * 예상치 못한 예외 발생 시, INTERNAL_SERVER_ERROR(500) 상태와 함께 응답을 반환.
     *
     * @param request   HTTP 요청 정보
     * @param ex        발생한 Exception
     */
    @ExceptionHandler(Exception.class)
    public Object handleGenericException(HttpServletRequest request, Exception ex) {

        // 일반적인 실패 상태 코드로 응답 생성
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(BaseResponse.error(ResponseCode.FAIL, ResponseCode.FAIL.getDefaultMessage()));
    }



}
