package com.innomes.main.exception.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.exception.CAuthenticationEntryPointException;
import com.innomes.main.response.model.CommonResult;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {
	@GetMapping(value = "/entrypoint")
	public CommonResult entrypointException() {
		throw new CAuthenticationEntryPointException();
	}
	
	@GetMapping(value = "/accessdenied")
	public CommonResult accessdeniedException() {
	    throw new AccessDeniedException("");
	}
}
