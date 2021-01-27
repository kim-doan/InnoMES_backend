package com.innomes.main.master.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.exception.CProcessSaveException;
import com.innomes.main.master.dto.MasterProcessDTO;
import com.innomes.main.master.param.MasterProcessParam;
import com.innomes.main.master.service.MasterProcessService;
import com.innomes.main.response.model.CommonResult;
import com.innomes.main.response.model.PageListResult;
import com.innomes.main.response.service.ResponseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "MasterProcessController")
public class MasterProcessController {

	@Autowired
	private ResponseService responseService;
	@Autowired
	private MasterProcessService masterProcessService;

	@ApiOperation(value = "공정 정보 조회", notes = "공정 정보를 반환합니다. (검색조건 필터링 가능)")
	@CrossOrigin
	@PostMapping("/master/processList")
	public PageListResult<MasterProcessDTO> getMaterProcessList(@RequestBody(required = false) MasterProcessParam masterProcessParam, final Pageable pageable) {

		if (masterProcessParam == null)
			masterProcessParam = new MasterProcessParam(); // 전체 조회

		return responseService.getPageListResult(MasterProcessDTO.class, masterProcessService.getMaterProcessList(masterProcessParam, pageable));
	}

	//공정정보 insert & update
	@ApiOperation(value = "공정 정보 저장", notes = "공정 정보를 저장합니다.")
	@CrossOrigin
	@PostMapping("/master/processInfo/save")
	public CommonResult saveMasterProcess(@RequestBody(required = true) @Valid List<MasterProcessParam> paramList) {

		boolean result = masterProcessService.saveMasterProcess(paramList);

		if (result == true) {
			return responseService.getSuccessResult();
		} else {
			throw new CProcessSaveException();
		}
	}
}
