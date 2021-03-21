package com.innomes.main.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.exception.CManufactureProcessSaveException;
import com.innomes.main.master.dto.MasterManufactureItemDTO;
import com.innomes.main.master.dto.MasterManufactureProcessDTO;
import com.innomes.main.master.param.MasterManufactureProcessParam;
import com.innomes.main.master.param.MasterProductParam;
import com.innomes.main.master.service.MasterManufactureProcessService;
import com.innomes.main.response.model.CommonResult;
import com.innomes.main.response.model.ListResult;
import com.innomes.main.response.model.PageListResult;
import com.innomes.main.response.service.ResponseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "MasterManufactureProcessController")
public class MasterManufactureProcessController {
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private MasterManufactureProcessService masterManufactureProcessService;
	
	@ApiOperation(value = "제조공정정보 조회", notes = "제품 전체 기준으로 제조공정정보를 조회합니다. (검색조건 필터링 가능)")
	@CrossOrigin
	@PostMapping("/master/manufactureItem")
	private PageListResult<MasterManufactureItemDTO> getManufactureItem(@RequestBody(required = false) MasterProductParam masterProductParam, final Pageable pageable) {
		if (masterProductParam == null) masterProductParam = new MasterProductParam(); // 전체 조회
		
		return responseService.getPageListResult(MasterManufactureItemDTO.class, masterManufactureProcessService.getManufactureItem(masterProductParam, pageable));
	}
	
	@ApiOperation(value = "제조공정정보 조회", notes = "제품 단건 기준으로 제조공정정보를 조회합니다. (검색조건 필터링 가능)")
	@CrossOrigin
	@PostMapping("/master/manufactureProcess")
	private ListResult<MasterManufactureProcessDTO> getManufactureProcess(@RequestBody(required = false) MasterManufactureProcessParam masterManufactureProcessParam) {
		if (masterManufactureProcessParam == null) masterManufactureProcessParam = new MasterManufactureProcessParam(); // 전체 조회
		
		return responseService.getListResult(MasterManufactureProcessDTO.class, masterManufactureProcessService.getManufactureProcess(masterManufactureProcessParam));
	}
	
	@ApiOperation(value = "제조공정정보 개정", notes = "제조공정정보를 개정합니다.")
	@CrossOrigin
	@PostMapping("/master/manufactureProcess/save")
	private CommonResult setManufactureProcess(@RequestBody(required = true) MasterManufactureProcessParam masterManufactureProcessParam) {		
		boolean result = masterManufactureProcessService.saveManufactureProcess(masterManufactureProcessParam);
		
		if(result == true) {
			return responseService.getSuccessResult();
		} else {
			throw new CManufactureProcessSaveException();
		}
	}
}
