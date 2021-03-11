package com.innomes.main.pool.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.code.model.COD100;
import com.innomes.main.master.dto.MasterProductDTO;
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
}
