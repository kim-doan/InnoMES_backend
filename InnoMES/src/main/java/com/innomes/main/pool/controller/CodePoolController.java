package com.innomes.main.pool.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.code.model.COD100;
import com.innomes.main.code.model.COD200;
import com.innomes.main.code.model.COD300;
import com.innomes.main.code.model.COD400;
import com.innomes.main.pool.service.CodePoolService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "CodePoolController")
public class CodePoolController {
	@Autowired
	private CodePoolService codePoolService;
	
	@CrossOrigin
	@ApiOperation(value = "사용자코드 전체 조회", notes = "사용자코드 전체 정보를 반환합니다.(캐시)")
	@GetMapping("/pool/code/userCode")
	public Map<String, COD100> getUserCodeLookUp() {
		
		return codePoolService.getCOD100Map();
	}
	@CrossOrigin
	@ApiOperation(value = "불량유형코드 전체 조회", notes = "불량유형코드 전체 정보를 반환합니다.(캐시)")
	@GetMapping("/pool/code/badCode")
	public Map<String, COD200> getBadCodeLookUp() {
		
		return codePoolService.getCOD200Map();
	}
	@CrossOrigin
	@ApiOperation(value = "비가동코드 전체 조회", notes = "비가동코드 전체 정보를 반환합니다.(캐시)")
	@GetMapping("/pool/code/stopCode")
	public Map<String, COD300> getStopCodeLookUp() {
		
		return codePoolService.getCOD300Map();
	}
	@CrossOrigin
	@ApiOperation(value = "고장유형코드 전체 조회", notes = "고장유형코드 전체 정보를 반환합니다.(캐시)")
	@GetMapping("/pool/code/failCode")
	public Map<String, COD400> getFailCodeLookUp() {
		
		return codePoolService.getCOD400Map();
	}
}
