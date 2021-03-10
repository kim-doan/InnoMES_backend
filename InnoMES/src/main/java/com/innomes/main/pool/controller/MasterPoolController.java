package com.innomes.main.pool.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.code.model.COD100;
import com.innomes.main.master.dto.MasterCompanyDTO;
import com.innomes.main.master.dto.MasterMaterialDTO;
import com.innomes.main.master.dto.MasterProcessDTO;
import com.innomes.main.master.dto.MasterProductDTO;
import com.innomes.main.master.dto.MasterSpareDTO;
import com.innomes.main.master.dto.MasterToolDTO;
import com.innomes.main.master.dto.MasterUserDTO;
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
	@ApiOperation(value = "제품 전체 조회", notes = "제품 전체 정보를 반환합니다.(캐시)")
	@GetMapping("/pool/master/product")
	public Map<String, MasterProductDTO> getProductLookUp() {
		
		return masterPoolService.getMST111Map();
	}
	
	@CrossOrigin
	@ApiOperation(value = "자재 전체 조회", notes = "자재 전체 정보를 반환합니다.(캐시)")
	@GetMapping("/pool/master/material")
	public Map<String, MasterMaterialDTO> getMaterialLookUp() {
		
		return masterPoolService.getMST112Map();
	}
	
	@CrossOrigin
	@ApiOperation(value = "공구 전체 조회", notes = "공구 전체 정보를 반환합니다.(캐시)")
	@GetMapping("/pool/master/tool")
	public Map<String, MasterToolDTO> getToolLookUp() {
		
		return masterPoolService.getMST113Map();
	}
	
	@CrossOrigin
	@ApiOperation(value = "예비품 전체 조회", notes = "예비품 전체 정보를 반환합니다.(캐시)")
	@GetMapping("/pool/master/part")
	public Map<String, MasterSpareDTO> getPartLookUp() {
		
		return masterPoolService.getMST114Map();
	}
	
	@CrossOrigin
	@ApiOperation(value = "공정 전체 조회", notes = "공정 전체 정보를 반환합니다.(캐시)")
	@GetMapping("/pool/master/process")
	public Map<String, MasterProcessDTO> getProcessLookUp() {
		
		return masterPoolService.getMST120Map();
	}
	
	@CrossOrigin
	@ApiOperation(value = "유저 전체 조회", notes = "유저 전체 정보를 반환합니다.(캐시)")
	@GetMapping("/pool/master/user")
	public Map<String, MasterUserDTO> getUserLookUp() {
		
		return masterPoolService.getMST140Map();
	}
	
	@CrossOrigin
	@ApiOperation(value = "거래처 전체 조회", notes = "거래처 전체 정보를 반환합니다.(캐시)")
	@GetMapping("/pool/master/company")
	public Map<String, MasterCompanyDTO> getCompanyLookUp() {
		
		return masterPoolService.getMST150Map();
	}
}
