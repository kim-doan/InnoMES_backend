package com.innomes.main.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.exception.CCompanySaveException;
import com.innomes.main.master.dto.MasterCompanyDTO;
import com.innomes.main.master.model.MST150;
import com.innomes.main.master.param.MasterCompanyParam;
import com.innomes.main.master.service.MasterCompanyService;
import com.innomes.main.response.model.CommonResult;
import com.innomes.main.response.model.PageListResult;
import com.innomes.main.response.service.ResponseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "MasterCompanyController")
public class MasterCompanyController {
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private MasterCompanyService masterCompanyService;
	
	@CrossOrigin
	@ApiOperation(value = "거래처 조회", notes = "거래처정보를 반환합니다.")
	@PostMapping("/master/company")
	public PageListResult<MasterCompanyDTO> getCompanyList(@RequestBody(required = false) MasterCompanyParam masterCompanyParam, final Pageable pageable){
		if(masterCompanyParam == null)
			masterCompanyParam = new MasterCompanyParam();
		
		return responseService.getPageListResult(MasterCompanyDTO.class, masterCompanyService.getCompInfo(masterCompanyParam, pageable));
	}
	
	@CrossOrigin
	@ApiOperation(value = "거래처 정보 저장", notes = "거래처 정보를 저장합니다.")
	@PostMapping("/master/company/save")
	public CommonResult setCompInfo(@RequestBody(required = true) List<MST150> compParam) {
		boolean result = masterCompanyService.setCompInfo(compParam);
		
		if(result) {
			return responseService.getSuccessResult();
		}else {
			throw new CCompanySaveException();
		}
	}
}
