package com.innomes.main.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.exception.CProductSaveException;
import com.innomes.main.master.dto.MasterToolDTO;
import com.innomes.main.master.param.MasterToolParam;
import com.innomes.main.master.service.MasterToolService;
import com.innomes.main.response.model.CommonResult;
import com.innomes.main.response.model.ListResult;
import com.innomes.main.response.service.ResponseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "MasterToolController")
public class MasterToolController {

	@Autowired
	private ResponseService responseService;

	@Autowired
	private MasterToolService masterToolService;

	@ApiOperation(value = "공구 조회", notes = "공구정보를 반환합니다. (검색조건 필터링 가능)")
	@CrossOrigin
	@PostMapping("/master/item/tool")
	public ListResult<MasterToolDTO> selectToolList(@RequestBody(required = false) MasterToolParam masterToolParam, final Pageable pageable) {
		
		if (masterToolParam == null) masterToolParam = new MasterToolParam(); // 전체 조회

		return responseService.getListResult(MasterToolDTO.class, masterToolService.getToolList(masterToolParam, pageable));
	}
	
	@ApiOperation(value = "공구 정보 저장", notes = "공구정보를 저장합니다.")
	@CrossOrigin
	@PostMapping("/master/item/tool/save")
	public CommonResult saveProduct(@RequestBody(required = true) List<MasterToolParam> masterToolParam) {
		boolean result = masterToolService.saveTool(masterToolParam);
		
		if(result == true) {
			return responseService.getSuccessResult();
		} else {
			throw new CProductSaveException();
		}
	}
}
