package com.innomes.main.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.master.dto.MasterManufactureItemDTO;
import com.innomes.main.master.param.MasterProductParam;
import com.innomes.main.master.service.MasterManufactureProcessService;
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
	
	@ApiOperation(value = "제조공정정보 조회", notes = "제조공정정보를 반환합니다. (검색조건 필터링 가능)")
	@CrossOrigin
	@PostMapping("/master/manufactureProcess")
	private PageListResult<MasterManufactureItemDTO> getManufactureProcess(@RequestBody(required = false) MasterProductParam masterProductParam, final Pageable pageable) {
		if (masterProductParam == null) masterProductParam = new MasterProductParam(); // 전체 조회
		
		return responseService.getPageListResult(MasterManufactureItemDTO.class, masterManufactureProcessService.getManufactureItem(masterProductParam, pageable));
	}
}
