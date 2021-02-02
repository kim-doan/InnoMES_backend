package com.innomes.main.pool.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.code.model.COD100;
import com.innomes.main.master.dto.MasterProductDTO;
import com.innomes.main.pool.service.MasterPoolService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "MasterPoolController")
public class MasterPoolController {
	@Autowired
	private MasterPoolService masterPoolService;
	
	@CrossOrigin
	@ApiOperation(value = "사용자코드 전체 조회", notes = "사용자코드 전체 정보를 반환합니다.(캐시)")
	@GetMapping("/pool/master/code")
	public Map<String, COD100> getUserCodeLookUp() {
		
		return masterPoolService.getCOD100Map();
	}
	
	@CrossOrigin
	@ApiOperation(value = "제품 전체 조회", notes = "제품 전체 정보를 반환합니다.(캐시)")
	@GetMapping("/pool/master/product")
	public Map<String, MasterProductDTO> getProductLookUp() {
		
		return masterPoolService.getMST111Map();
	}
}
