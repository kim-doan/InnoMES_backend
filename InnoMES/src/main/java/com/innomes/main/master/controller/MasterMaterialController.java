package com.innomes.main.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.exception.CProductSaveException;
import com.innomes.main.master.dto.MasterMaterialDTO;
import com.innomes.main.master.param.MasterMaterialParam;
import com.innomes.main.master.service.MasterMaterialService;
import com.innomes.main.response.model.CommonResult;
import com.innomes.main.response.model.PageListResult;
import com.innomes.main.response.service.ResponseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "MasterProductController")
public class MasterMaterialController {
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private MasterMaterialService masterMaterialService;
	
	@ApiOperation(value = "자재 전체 조회", notes = "자재정보 전체를 반환합니다. (검색조건 필터링 가능)")
	@CrossOrigin
	@PostMapping("/master/item/materials")
	public PageListResult<MasterMaterialDTO> getMaterial(@RequestBody(required = false) MasterMaterialParam masterMaterialParam, final Pageable pageable){
		if(masterMaterialParam == null)
			masterMaterialParam = new MasterMaterialParam();
		
		return responseService.getPageListResult(MasterMaterialDTO.class, masterMaterialService.getMaterial(masterMaterialParam, pageable));
	}
	
	@ApiOperation(value = "자재 정보 저장", notes = "자재정보를 저장합니다.")
	@CrossOrigin
	@PostMapping("/master/item/materials/save")
	public CommonResult setMaterials(@RequestBody(required = true) MasterMaterialParam[] masterMaterialParam){
		boolean result = masterMaterialService.setMaterials(masterMaterialParam);
		
		if(result == true) {
			return responseService.getSuccessResult();
		} else {
			throw new CProductSaveException();
		}
	}
}
