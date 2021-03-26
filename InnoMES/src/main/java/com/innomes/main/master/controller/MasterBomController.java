package com.innomes.main.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.master.dto.MasterBomDTO;
import com.innomes.main.master.param.MasterBomParam;
import com.innomes.main.master.service.MasterBomService;
import com.innomes.main.response.model.ListResult;
import com.innomes.main.response.service.ResponseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "MasterBomController")
public class MasterBomController {
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private MasterBomService masterBomService;
	
	@ApiOperation(value = "BOM 정보 조회", notes = "입력한 제품과 공정의 투입소재를 반환합니다.")
	@CrossOrigin
	@PostMapping("/master/bomList")
	private ListResult<MasterBomDTO> getBomList(@RequestBody(required = false) MasterBomParam masterBomParam) {
		if (masterBomParam == null) masterBomParam = new MasterBomParam(); // 전체 조회
		
		List<MasterBomDTO> list = masterBomService.getBomList(masterBomParam);
		
		return responseService.getListResult(MasterBomDTO.class, list);
	}
}
