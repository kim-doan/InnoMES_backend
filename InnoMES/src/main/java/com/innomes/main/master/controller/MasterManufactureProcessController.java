package com.innomes.main.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.exception.CManufactureProcessSaveException;
import com.innomes.main.exception.CParametersShortException;
import com.innomes.main.master.dto.MasterManufactureItemDTO;
import com.innomes.main.master.dto.MasterManufactureProcessDTO;
import com.innomes.main.master.param.MasterManufactureProcessParam;
import com.innomes.main.master.param.MasterProductParam;
import com.innomes.main.master.service.MasterManufactureProcessService;
import com.innomes.main.response.model.CommonResult;
import com.innomes.main.response.model.ListResult;
import com.innomes.main.response.model.PageListResult;
import com.innomes.main.response.model.SingleResult;
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
	@PostMapping("/master/manufactureProcess")
	private PageListResult<MasterManufactureItemDTO> getManufactureItem(@RequestBody(required = false) MasterProductParam param, 
			final Pageable pageable) {
		if (param == null) param = new MasterProductParam(); // 전체 조회
		
		return responseService.getPageListResult(MasterManufactureItemDTO.class, masterManufactureProcessService.getManufactureItem(param, pageable));
	}
	
	@ApiOperation(value = "제조공정정보 이력보기", notes = "선택 제품의 제조공정 개정 이력을 조회합니다.")
	@CrossOrigin
	@PostMapping("/master/manufactureProcess/revLog")
	private ListResult<MasterManufactureProcessDTO> getManufactureProcessRevLog(@RequestBody(required = true) MasterManufactureProcessParam param) {
		if(param == null) throw new CParametersShortException();
		
		return responseService.getListResult(MasterManufactureProcessDTO.class, masterManufactureProcessService.getManufactureProcessRevLog(param));
	}
	
	@ApiOperation(value = "제조공정정보 개정", notes = "제조공정정보를 개정합니다.")
	@CrossOrigin
	@PostMapping("/master/manufactureProcess/save")
	private CommonResult saveManufactureProcess(@RequestBody MasterManufactureProcessParam param) {		
		boolean result = masterManufactureProcessService.saveManufactureProcess(param);
		
		if(result == true) {
			return responseService.getSuccessResult();
		} else {
			throw new CManufactureProcessSaveException();
		}
	}
	
	@ApiOperation(value = "제조공정정보 수정", notes = "제조공정정보를 수정합니다.")
	@CrossOrigin
	@PostMapping("master/manufactureProcess/update")
	private CommonResult updateManufactureProcess(@RequestBody MasterManufactureProcessParam param) {
		boolean result = masterManufactureProcessService.updateManufactureProcess(param);
		
		if(result == true) {
			return responseService.getSuccessResult();
		} else {
			throw new CManufactureProcessSaveException();
		}
	}
}
