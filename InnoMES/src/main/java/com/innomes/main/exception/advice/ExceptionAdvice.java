/*
 * 2019.09.24
 * 김도안
 * resources/i18n/exception_ko.yml에 있는 code와 메시지를 가져와 결과값으로 전송하는 부분
 * 
 */

package com.innomes.main.exception.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.innomes.main.exception.CAuthenticationEntryPointException;
import com.innomes.main.exception.CSessionNotFoundException;
import com.innomes.main.exception.CUserNotFoundException;
import com.innomes.main.response.model.CommonResult;
import com.innomes.main.response.service.ResponseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
	
	private final ResponseService responseService;

	private final MessageSource messageSource;

//	@Autowired
//	private LabelInfoService labelInfoService;

	private String getMessage(String code) {
		return getMessage(code, null);
	}

	private String getMessage(String code, Object[] args) {
		return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
	}

	//	//알수없는 오류
	//	@ExceptionHandler(Exception.class)
	//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	//	protected CommonResult defaultException(HttpServletRequest request, Exception e) {
	//		//예외 처리 코드를 MessageSource에서 가져오도록 설정
	//		return responseService.getFailResult("E0001", labelInfoService.convertLabelName("E0001", "KR"));
	//	}

	//요청한 세션이 비어있을 경우
	@ExceptionHandler(CSessionNotFoundException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult sessionNotFoundException(HttpServletRequest request, CSessionNotFoundException e) {
		return responseService.getFailResult("E0002", "세션이 비어있습니다.");
	}

	//유저 정보 조회 오류
	@ExceptionHandler(CUserNotFoundException.class)
	//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult userNotFoundException(HttpServletRequest request, CUserNotFoundException e) {
		//예외 처리 코드를 MessageSource에서 가져오도록 설정
		return responseService.getFailResult("E0003", "유저정보조회오");
	}

	//토큰 만료 및 없을 경우 오류 처리
	@ExceptionHandler(CAuthenticationEntryPointException.class)
	public CommonResult authenticationEntryPointException(HttpServletRequest request, CAuthenticationEntryPointException e) {
		return responseService.getFailResult("E0005", "토큰이 만료되었습니다.");
	}

	//토큰 권한 오류
	@ExceptionHandler(AccessDeniedException.class)
	public CommonResult AccessDeniedException(HttpServletRequest request, AccessDeniedException e) {
		return responseService.getFailResult("E0006", "해당 리소스에 접근권한이 없습니다.");
	}
}