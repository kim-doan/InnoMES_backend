package com.innomes.main.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.master.dto.MasterLocationDTO;
import com.innomes.main.master.dto.MasterToolDTO;
import com.innomes.main.master.param.MasterLocationParam;
import com.innomes.main.master.service.MasterLocationService;
import com.innomes.main.response.model.PageListResult;
import com.innomes.main.response.service.ResponseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "MasterLocationController")
public class MasterLocationController {

	@Autowired
	private MasterLocationService masterLocationService;
	
	@Autowired
	private ResponseService responseService;
	
	@ApiOperation(value = "적재위치 조회", notes = "적재위치를 반환합니다.")
	@CrossOrigin
	@PostMapping("/master/locationInfo")
	public PageListResult<MasterLocationDTO> getMasterLocationList(@RequestBody(required = false) MasterLocationParam masterLocationParam, final Pageable pageable){
		
		if (masterLocationParam == null) masterLocationParam = new MasterLocationParam(); // 전체 조회

		return responseService.getPageListResult(MasterToolDTO.class, masterLocationService.getMasterLocationList(masterLocationParam, pageable));
		
	}
}
