package com.innomes.main.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.exception.CProductSaveException;
import com.innomes.main.master.dto.MasterSpareDTO;
import com.innomes.main.master.param.MasterSpareParam;
import com.innomes.main.master.service.MasterSpareService;
import com.innomes.main.response.model.CommonResult;
import com.innomes.main.response.model.PageListResult;
import com.innomes.main.response.service.ResponseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "MasterSpareController")
public class MasterSpareController {
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private MasterSpareService masterSpareService;
	
	@CrossOrigin
	@ApiOperation(value = "예비품 전체조회", notes = "예비품정보 전체를 반환합니다. (검색조건 필터링 가능")
	@PostMapping("/master/item/spares")
	public PageListResult<MasterSpareDTO> getSpareList(@RequestBody(required = false) MasterSpareParam masterSpareParam, final Pageable pageable){
		if(masterSpareParam == null)
			masterSpareParam = new MasterSpareParam();
		
		return responseService.getPageListResult(MasterSpareDTO.class, masterSpareService.getSpares(masterSpareParam, pageable));
	}
	
	@CrossOrigin
	@ApiOperation(value = "예비품 정보 저장", notes = "예비품 정보를 저장합니다.")
	@PostMapping("/master/item/spares/save")
	public CommonResult setSpares(@RequestBody(required = true) MasterSpareParam[] masterSpareParams) {
		boolean result = masterSpareService.saveSpare(masterSpareParams);
		if(result == true) {
			return responseService.getSuccessResult();
		} else {
			throw new CProductSaveException();
		}
	}
}
